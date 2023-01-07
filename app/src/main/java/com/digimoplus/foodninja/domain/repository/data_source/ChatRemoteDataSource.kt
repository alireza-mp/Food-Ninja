package com.digimoplus.foodninja.domain.repository.data_source

import com.digimoplus.foodninja.data.api.soketio.InitialRoomData
import com.digimoplus.foodninja.data.api.soketio.MessageDto
import io.socket.emitter.Emitter.Listener

interface ChatRemoteDataSource {

    suspend fun listeningConnect(emitter: Listener)
    suspend fun disconnect(initialRoomData: InitialRoomData)
    suspend fun listeningToNewUser(emitter: Listener)
    suspend fun listeningToUserLeft(emitter: Listener)
    suspend fun listeningToNewMessage(emitter: Listener)
    suspend fun listeningToStartTypeIng(emitter: Listener)
    suspend fun listeningToStopTypeIng(emitter: Listener)
    suspend fun sendMessage(messageDto: MessageDto)
    suspend fun startTypeIng(roomName: String)
    suspend fun stopTypeIng(roomName: String)
    fun initialRoom(initialRoomData: InitialRoomData)
    fun connect()

}