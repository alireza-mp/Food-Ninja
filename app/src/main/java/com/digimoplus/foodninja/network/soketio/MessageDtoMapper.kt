package com.digimoplus.foodninja.network.soketio

import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.domain.util.DomainMapper

class MessageDtoMapper() : DomainMapper<MessageDto, Message> {
    override fun mapToDomainModel(model: MessageDto): Message {
        return Message(
            userName = model.userName,
            messageContent = model.messageContent,
            roomName = model.roomName,
            messageType = MessageType.RECEIVED_MESSAGE
        )
    }

    override fun mapFromDomainModel(domainModel: Message): MessageDto {
        return MessageDto(
            userName = domainModel.userName,
            messageContent = domainModel.messageContent,
            roomName = domainModel.roomName
        )
    }

    fun mapToDomainModel(model: MessageDto, messageType: MessageType): Message {
        val message = mapToDomainModel(model)
        message.messageType = messageType
        return message
    }

}