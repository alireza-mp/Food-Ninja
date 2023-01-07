package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.data.api.soketio.InitialRoomData
import com.digimoplus.foodninja.data.api.soketio.MessageDto
import com.digimoplus.foodninja.data.api.soketio.MessageDtoMapper
import com.digimoplus.foodninja.data.api.soketio.MessageType
import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.domain.repository.ChatRepository
import com.digimoplus.foodninja.domain.repository.data_source.ChatRemoteDataSource
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.google.gson.Gson
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl
@Inject constructor(
    private val mapper: MessageDtoMapper,
    private val dataStore: DataStoreLocalDataSource,
    private val chatRemoteDataSource: ChatRemoteDataSource,
    private val gson: Gson,
) : ChatRepository {

    override suspend fun listeningToMessages(userName: String, roomName: String): Flow<Message> =
        callbackFlow {

            val userId = dataStore.getUserId().toString()

            val onConnect = Emitter.Listener {
                // send room user name and room name to server
                val data = InitialRoomData(userId, roomName)
                chatRemoteDataSource.initialRoom(data)
            }

            val onNewUser = Emitter.Listener {
                val name = it[0] as String
                val message = Message(
                    userName = name,
                    roomName = roomName,
                    messageType = MessageType.NEW_USER
                )
                trySend(message)
            }

            val onUserLeft = Emitter.Listener {
                val leftUserName = it[0] as String
                val message = Message(
                    userName = leftUserName,
                    roomName = roomName,
                    messageType = MessageType.USER_LEFT
                )
                trySend(message)
            }

            val onNewMessage = Emitter.Listener {
                val chat = gson.fromJson(it[0].toString(), MessageDto::class.java)
                val data = mapper.mapToDomainModel(chat, MessageType.RECEIVED_MESSAGE)
                trySend(data)
            }


            chatRemoteDataSource.listeningConnect(onConnect)
            chatRemoteDataSource.listeningToNewMessage(onNewMessage)
            chatRemoteDataSource.listeningToNewUser(onNewUser)
            chatRemoteDataSource.listeningToUserLeft(onUserLeft)
            chatRemoteDataSource.connect()

            awaitClose {
                channel.close()
            }
        }

    override suspend fun disconnect(userName: String, roomName: String) {
        val data = InitialRoomData(userName = dataStore.getUserId().toString(), roomName)
        chatRemoteDataSource.disconnect(data)
    }


    override suspend fun sendMessage(message: Message) {
        withContext(Dispatchers.IO) {
            val data: MessageDto = mapper.mapFromDomainModel(message)
            chatRemoteDataSource.sendMessage(data)
        }
    }

    override suspend fun listeningToTypeIng(): Flow<Boolean> = callbackFlow {

        val startTypeIng = Emitter.Listener {
            trySend(true)
        }

        val stopTypeIng = Emitter.Listener {
            trySend(false)
        }

        chatRemoteDataSource.listeningToStartTypeIng(startTypeIng)
        chatRemoteDataSource.listeningToStopTypeIng(stopTypeIng)

        awaitClose {
            channel.close()
        }
    }

    override suspend fun startTypeIng(roomName: String) =
        chatRemoteDataSource.startTypeIng(roomName)

    override suspend fun stopTypeInt(roomName: String) = chatRemoteDataSource.stopTypeIng(roomName)
}



