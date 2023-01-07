package com.digimoplus.foodninja.domain.useCase.chat

import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase
@Inject
constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(message: Message) = chatRepository.sendMessage(message)

}