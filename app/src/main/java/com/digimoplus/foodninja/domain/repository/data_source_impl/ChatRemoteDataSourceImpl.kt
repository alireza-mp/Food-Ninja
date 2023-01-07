package com.digimoplus.foodninja.domain.repository.data_source_impl

import com.digimoplus.foodninja.data.api.soketio.InitialRoomData
import com.digimoplus.foodninja.data.api.soketio.MessageDto
import com.digimoplus.foodninja.data.api.soketio.SocketEvents
import com.digimoplus.foodninja.domain.repository.data_source.ChatRemoteDataSource
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter.Listener
import javax.inject.Inject

class ChatRemoteDataSourceImpl
@Inject
constructor(
    private val socket: Socket,
    private val gson: Gson,
) : ChatRemoteDataSource {

    override suspend fun listeningConnect(emitter: Listener) {
        socket.on(Socket.EVENT_CONNECT, emitter)
    }

    override suspend fun disconnect(initialRoomData: InitialRoomData) {
        val jsonData = gson.toJson(initialRoomData)
        socket.emit(SocketEvents.UNSUBSCRIBE.event, jsonData)
    }

    override suspend fun listeningToNewUser(emitter: Listener) {
        socket.on(SocketEvents.NEW_USER.event, emitter)
    }

    override suspend fun listeningToUserLeft(emitter: Listener) {
        socket.on(SocketEvents.USER_LEFT.event, emitter)
    }

    override suspend fun listeningToNewMessage(emitter: Listener) {
        socket.on(SocketEvents.UPDATE_CHAT.event, emitter)
    }

    override suspend fun listeningToStartTypeIng(emitter: Listener) {
        socket.on(SocketEvents.START_TYPE_ING.event, emitter)
    }

    override suspend fun listeningToStopTypeIng(emitter: Listener) {
        socket.on(SocketEvents.STOP_TYPE_ING.event, emitter)
    }

    override suspend fun sendMessage(messageDto: MessageDto) {
        val jsonData = gson.toJson(messageDto)
        socket.emit(SocketEvents.NEW_MESSAGE.event, jsonData)
    }

    override suspend fun startTypeIng(roomName: String) {
        socket.emit(SocketEvents.START_TYPE_ING.event, roomName)
    }

    override suspend fun stopTypeIng(roomName: String) {
        socket.emit(SocketEvents.STOP_TYPE_ING.event, roomName)
    }

    override fun initialRoom(initialRoomData: InitialRoomData) {
        val jsonData = gson.toJson(initialRoomData)
        socket.emit(SocketEvents.SUBSCRIBE.event, jsonData)
    }

    override fun connect() {
        socket.connect()
    }


}