package com.digimoplus.foodninja.domain.useCase.chat

import com.digimoplus.foodninja.domain.repository.ChatRepository
import javax.inject.Inject

class DisconnectChatUseCase
@Inject
constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(userName: String, roomName: String) =
        chatRepository.disconnect(userName, roomName)

}