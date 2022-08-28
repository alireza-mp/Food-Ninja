package com.digimoplus.foodninja.domain.model

import com.digimoplus.foodninja.data.api.soketio.MessageType

data class Message(
    val userName: String,
    val messageContent: String = "",
    val roomName: String,
    var messageType: MessageType,
)