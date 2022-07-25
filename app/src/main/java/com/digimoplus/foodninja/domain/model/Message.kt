package com.digimoplus.foodninja.domain.model

import com.digimoplus.foodninja.network.soketio.MessageType

data class Message(
    val userName: String,
    val messageContent: String = "",
    val roomName: String,
    var messageType: MessageType,
)