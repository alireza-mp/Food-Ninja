package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.sign_up

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(
    private val repository: SignUpRepository,
) : ViewModel() {


    val name: MutableState<String> = mutableStateOf("")
    val email: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val checkOne: MutableState<Boolean> = mutableStateOf(false)
    val checkTwo: MutableState<Boolean> = mutableStateOf(false)

    suspend fun register(state: SnackbarHostState) {
        when {
            name.value.length < 4 -> {
                state.showSnackbar("Invalid Name")
            }
            email.value.length < 9 -> {
                state.showSnackbar("Invalid Email")
            }
            password.value.length < 7 -> {
                state.showSnackbar("Invalid Password")
            }
            !checkOne.value ->{
                state.showSnackbar("Invalid checkOne")
            }
            !checkTwo.value->{
                state.showSnackbar("Invalid checkTwo")
            }
            else -> {
                registerUser(state)
            }
        }
    }

    private fun registerUser(state: SnackbarHostState) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val register = repository.registerUser(name.value, email.value, password.value)
            withContext(Dispatchers.Main) {
                loading.value = false
                state.showSnackbar(message = register.message)
            }
        }
    }

}