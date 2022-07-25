package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Message
import kotlinx.coroutines.flow.Flow


interface ChatDetailRepository {

    // connect to chat server // listening messages
    suspend fun connect(userName: String, roomName: String): Flow<Message>

    // disconnect from chat server
    fun disconnect(userName: String, roomName: String)

    // send message to chat server
    suspend fun sendMessage(message: Message)

    // notify the server starting typing
    suspend fun startTypeIng(roomName: String)

    // notify the server stop typing
    suspend fun stopTypeInt(roomName: String)

    // listening other user is typing from server
    suspend fun registerTypeIng(): Flow<Boolean>

}