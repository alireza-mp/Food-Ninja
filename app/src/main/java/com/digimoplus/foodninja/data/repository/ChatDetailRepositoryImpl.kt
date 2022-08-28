package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.data.api.soketio.*
import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.domain.repository.ChatDetailRepository
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatDetailRepositoryImpl
@Inject
constructor(
    private val socket: Socket,
    private val gson: Gson,
    private val mapper: MessageDtoMapper,
) : ChatDetailRepository {

    override suspend fun connect(userName: String, roomName: String) = callbackFlow<Message> {
        withContext(Dispatchers.IO) {

            //first connect
            val onConnect = Emitter.Listener {

                // socket id
                /*  val socketID: String = socket.id()
                  Log.i(Constants.TAG, "callSocket: $socketID")*/

                // send room user name and room name to server
                val data = InitialRoomData(userName, roomName)
                val jsonData = gson.toJson(data)
                socket.emit(SocketEvents.SUBSCRIBE.event, jsonData)

            }

            // new user joined listener
            val onNewUser = Emitter.Listener {

                // new user joined
                // get new user name
                val name = it[0] as String
                val message = Message(
                    userName = name,
                    roomName = roomName,
                    messageType = MessageType.NEW_USER
                )

                trySend(message)
            }

            // is users left listener
            val onUserLeft = Emitter.Listener {

                val leftUserName = it[0] as String
                val message = Message(
                    userName = leftUserName,
                    roomName = roomName,
                    messageType = MessageType.USER_LEFT
                )

                trySend(message)

            }

            // listening messages from server
            val onUpdateChat = Emitter.Listener {
                val chat = gson.fromJson(it[0].toString(), MessageDto::class.java)
                val data = mapper.mapToDomainModel(chat, MessageType.RECEIVED_MESSAGE)
                trySend(data)

            }


            socket.on(Socket.EVENT_CONNECT, onConnect)
            socket.on(SocketEvents.UPDATE_CHAT.event, onUpdateChat)
            socket.on(SocketEvents.NEW_USER.event,
                onNewUser) // To know if the new user entered the room.
            socket.on(SocketEvents.USER_LEFT.event,
                onUserLeft) // To know if the user left the chatroom.
            socket.connect()

        }

        awaitClose {
            channel.close()
        }
    }

    override fun disconnect(userName: String, roomName: String) {

        val data = InitialRoomData(userName, roomName)
        val jsonData = gson.toJson(data)
        socket.emit(SocketEvents.UNSUBSCRIBE.event, jsonData)

    }


    override suspend fun sendMessage(
        message: Message,
    ) {
        withContext(Dispatchers.IO) {
            val data: MessageDto = mapper.mapFromDomainModel(message)
            val jsonData = gson.toJson(data)
            socket.emit(SocketEvents.NEW_MESSAGE.event, jsonData)

        }
    }

    override suspend fun registerTypeIng() = callbackFlow<Boolean> {
        withContext(Dispatchers.IO) {

            // is other users typing listener

            val startTypeIng = Emitter.Listener {
                trySend(true)
            }

            val stopTypeIng = Emitter.Listener {
                trySend(false)
            }

            socket.on(SocketEvents.START_TYPE_ING.event, startTypeIng)
            socket.on(SocketEvents.STOP_TYPE_ING.event, stopTypeIng)

        }
        awaitClose {
            channel.close()
        }
    }

    override suspend fun startTypeIng(roomName: String) {
        socket.emit(SocketEvents.START_TYPE_ING.event, roomName)
    }

    override suspend fun stopTypeInt(roomName: String) {
        socket.emit(SocketEvents.STOP_TYPE_ING.event, roomName)
    }
}



