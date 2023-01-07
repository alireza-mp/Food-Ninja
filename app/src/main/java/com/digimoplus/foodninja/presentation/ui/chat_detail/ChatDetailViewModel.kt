package com.digimoplus.foodninja.presentation.ui.chat_detail

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.data.api.soketio.MessageType
import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.domain.repository.ChatRepository
import com.digimoplus.foodninja.domain.useCase.chat.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel
@Inject
constructor(
    private val chatUseCases: ChatUseCases,
    private val chatRepository: ChatRepository,
) : ViewModel() {

    val lazyListState = LazyListState()

    // textField text
    val text = mutableStateOf("")

    // MessageType in message model  : userLeft / newUser / received messages / sent messages
    private val _messageList = mutableStateListOf<Message>()
    val messageList: List<Message> = _messageList

    // typing ui state
    val typeIngState = mutableStateOf(false)


    private var userName = ""
    private val roomName = "Food_Ninja_Room"

    // user change typing listener
    private var isTypeIng = false
    private var typeIngJob: Job? = null

    // connect to server
    // listening messages from server
    fun listeningToMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            chatUseCases.listeningToMessagesUseCase(
                userName = userName, // username in repository set by user id
                roomName = roomName,
            ) //listening from server : user left / user joined / received messages
                .onEach { message: Message ->

                    _messageList.add(message)
                    lazyListState.scrollToItem(_messageList.size - 1)

                }.launchIn(viewModelScope)
        }
    }

    // disconnect chat
    fun disconnect() {
        viewModelScope.launch(Dispatchers.IO) {
            chatUseCases.disconnectChatUseCase(userName, roomName)
        }
    }

    // send message to server
    fun sendMessage() {
        val messageText = text.value
        viewModelScope.launch(Dispatchers.IO) {

            val message = Message(
                userName = userName,
                messageContent = messageText,
                roomName = roomName,
                messageType = MessageType.SEND_MESSAGE
            )

            chatUseCases.sendMessagesUseCase(message)

            // update ui
            withContext(Dispatchers.Main) {
                _messageList.add(message) // add message to local list
                text.value = ""  // clear text field
                lazyListState.scrollToItem(_messageList.size - 1)  // scroll lazyList to last message
            }

        }
    }

    // listening other user is typIng form server and update typIngState
    fun listeningToTypeIng() {
        viewModelScope.launch(Dispatchers.IO) {
            chatUseCases.listeningToTypeIngUseCase().onEach { isTypeIng ->
                typeIngState.value = isTypeIng
            }.launchIn(viewModelScope)
        }
    }


    // check textField change listener
    fun onChangeText(value: String) {
        text.value = value
        viewModelScope.launch(Dispatchers.IO) {
            if (!isTypeIng) {
                isTypeIng = true
                // notify server when user start typeIng
                chatUseCases.startTypeIngUseCase(roomName)
            }
        }
        // cancel prior job on a new change in text
        typeIngJob?.cancel()
        typeIngJob = viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            isTypeIng = false
            // notify server when user stop typeIng
            chatUseCases.stopTypeIngUseCase(roomName)
        }
    }

}