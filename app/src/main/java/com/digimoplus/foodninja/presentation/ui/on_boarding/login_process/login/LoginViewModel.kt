package com.digimoplus.foodninja.presentation.ui.on_boarding.login_process.login

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.useCase.login.LoginUseCase
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import navigateAndClean
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    // email textField
    val email = mutableStateOf("")

    // password textField
    val password = mutableStateOf("")

    val snackBarState = SnackbarHostState()

    var uiState by mutableStateOf(UiState.Visible)
        private set

    //login user to server and save auth token
    fun loginUser(navController: NavController) {
        val email = email.value
        val password = password.value
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(email, password).onEach { result ->
                when (result) {
                    is RegisterState.Loading -> {
                        uiState = UiState.Loading
                    }
                    is RegisterState.Successful -> {
                        uiState = UiState.Visible
                        navController.navigateAndClean(
                            route = Screens.Main.route,
                            popUpRoute = Screens.Login.route
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