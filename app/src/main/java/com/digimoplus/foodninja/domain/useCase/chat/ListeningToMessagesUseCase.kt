package com.digimoplus.foodninja.domain.useCase.chat

import com.digimoplus.foodninja.domain.repository.ChatRepository
import javax.inject.Inject

class ListeningToMessagesUseCase
@Inject
constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(userName: String, roomName: String) =
        chatRepository.listeningToMessages(userName, roomName)

}