package com.digimoplus.foodninja.presentation.ui.chat_detail

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.network.soketio.MessageType
import com.digimoplus.foodninja.repository.ChatDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel
@Inject
constructor(
    private val repository: ChatDetailRepository,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    // lazy list state
    val lazyListState = LazyListState()

    // textField text
    val text = mutableStateOf("")

    // messages list
    // MessageType in message model  : userLeft / newUser / received messages / sended messages
    val messageList = mutableStateListOf<Message>()

    // typing ui state
    val typeIngState = mutableStateOf(false)

    // user name
    private var userName = ""

    // room name
    private val roomName = "Food_Ninja_Room"

    // user change typing listener
    private var isTypeIng = false

    //
    private var typIngJob: Job? = null

    // connect to server
    // register user name and room name
    // listening messages from server
    fun connect() {
        viewModelScope.launch(Dispatchers.Main) {
            getUserName()
            repository.connect(
                userName = userName,
                roomName = roomName,
            ) // here just listening from server : user left / user joined / received messages
                .onEach { message: Message ->
                    messageList.add(message)
                    lazyListState.scrollToItem(messageList.size - 1)
                }.launchIn(viewModelScope)
        }
    }

    // disconnect
    fun disconnect() {
        repository.disconnect(userName, roomName)
    }

    // send message to server
    fun sendMessage() {
        viewModelScope.launch {
            val message = Message(
                userName = userName,
                messageContent = text.value,
                roomName = roomName,
                messageType = MessageType.SEND_MESSAGE
            )
            repository.sendMessage(message)
            // add message to local list
            messageList.add(message)
            // clear text field
            text.value = ""
            // scroll lazyList to last message
            lazyListState.scrollToItem(messageList.size - 1)
        }
    }

    // get user name from datastore
    private suspend fun getUserName() {
        if (userName == "")
            userName = dataStore.data.first()[PreferencesKeys.userId].toString()
    }

    // listening other user is typIng form server and update typIngState
    fun registerTypeIng() {
        viewModelScope.launch {
            repository.registerTypeIng().onEach { isTypeIng ->
                typeIngState.value = isTypeIng
            }.launchIn(viewModelScope)
        }
    }


    //check textField change listener
    //notify server when user state typeIng
    // notify server when user stop typeIng
    fun onChangeText(value: String) {
        text.value = value
        viewModelScope.launch {
            if (!isTypeIng) {
                isTypeIng = true
                repository.startTypeIng(roomName)
            }
        }
        // cancel prior job on a new change in text
        typIngJob?.cancel()
        typIngJob = viewModelScope.launch {
            delay(1000)
            isTypeIng = false
            repository.stopTypeInt(roomName)
        }
    }

}