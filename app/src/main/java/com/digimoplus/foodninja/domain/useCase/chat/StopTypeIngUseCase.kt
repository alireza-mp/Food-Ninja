package com.digimoplus.foodninja.domain.useCase.chat

import com.digimoplus.foodninja.domain.repository.ChatRepository
import javax.inject.Inject

class StopTypeIngUseCase
@Inject
constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(roomName: String) = chatRepository.stopTypeInt(roomName)

}