package com.digimoplus.foodninja.domain.useCase.chat

data class ChatUseCases(
    val disconnectChatUseCase: DisconnectChatUseCase,
    val listeningToMessagesUseCase: ListeningToMessagesUseCase,
    val listeningToTypeIngUseCase: ListeningToTypeIngUseCase,
    val sendMessagesUseCase: SendMessageUseCase,
    val startTypeIngUseCase: StartTypeIngUseCase,
    val stopTypeIngUseCase: StopTypeIngUseCase,
)