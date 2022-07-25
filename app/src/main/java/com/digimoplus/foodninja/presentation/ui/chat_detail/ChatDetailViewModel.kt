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

    val lazyListState = LazyListState()
    val text = mutableStateOf("")
    val messageList = mutableStateListOf<Message>()
    val visibleTypeIng = mutableStateOf(false)
    private var userName = ""
    private val roomName = "Food_Ninja_Room"
    private var isTypeIng = false

    fun connect() {
        viewModelScope.launch(Dispatchers.Main) {
            getUserName()
            repository.connect(
                userName = userName,
                roomName = roomName,
            ).onEach { message ->
                messageList.add(message)
                lazyListState.scrollToItem(messageList.size - 1)
            }.launchIn(viewModelScope)
        }
    }

    fun disconnect() {
        repository.disconnect(userName, roomName)
    }

    fun sendMessage() {
        viewModelScope.launch {
            val message = Message(
                userName = userName,
                messageContent = text.value,
                roomName = roomName,
                messageType = MessageType.SEND_MESSAGE
            )
            repository.sendMessage(message)
            messageList.add(message)
            text.value = ""
            lazyListState.scrollToItem(messageList.size - 1)
        }
    }

    private var job: Job? = null

    private suspend fun getUserName() {
        if (userName == "")
            userName = dataStore.data.first()[PreferencesKeys.userId].toString()
    }

    fun onChangeText(value: String) {
        text.value = value
        viewModelScope.launch {
            if (!isTypeIng) {
                isTypeIng = true
                repository.startTypeIng(roomName)
            }
        }
        job?.cancel() // cancel prior job on a new change in text
        job = viewModelScope.launch {
            delay(1000)
            isTypeIng = false
            repository.stopTypeInt(roomName)
        }
    }

    fun registerTypeIng() {
        viewModelScope.launch {
            repository.registerTypeIng().onEach { isTypeIng ->
                visibleTypeIng.value = isTypeIng
            }.launchIn(viewModelScope)
        }
    }


}