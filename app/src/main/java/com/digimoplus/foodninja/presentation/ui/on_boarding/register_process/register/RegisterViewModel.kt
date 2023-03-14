package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.register

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.useCase.register.RegisterUseCase
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import navigateAndClean
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(UiState.Visible)
        private set

    val snackBarState = SnackbarHostState()

    // name textField
    val name = mutableStateOf("")

    // email textField
    val email = mutableStateOf("")

    // password textFiled
    val password = mutableStateOf("")

    // checkBox one state
    val checkOne = mutableStateOf(false)

    // check box two state
    val checkTwo = mutableStateOf(false)

    // register user to server 
    fun register(navController: NavController) {
        val name = name.value
        val email = email.value
        val password = password.value
        val checkOne = checkOne.value
        val checkTwo = checkTwo.value
        viewModelScope.launch {
            registerUseCase(name, email, password, checkOne, checkTwo).onEach { result ->
                when (result) {
                    is RegisterState.Loading -> {
                        uiState = UiState.Loading
                    }
                    is RegisterState.Successful -> {
                        uiState = UiState.Visible
                        navController.navigateAndClean(
                            route = Screens.CompleteRegister.createRoute(name),
                            popUpRoute = Screens.Register.route,
                        )
                    }
                    else -> {
                        uiState = UiState.NoInternet
                        snackBarState.showSnackbar(result.message)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}