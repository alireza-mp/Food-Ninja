package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_in_process.sign_in

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.repository.SignInRepository
import com.digimoplus.foodninja.repository.SignInRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    private val repository: SignInRepository,
) : ViewModel() {

    // email textField
    val email = mutableStateOf("")
    // password textField
    val password = mutableStateOf("")
    // snack bar state
    val snackBarState = SnackbarHostState()
    // ui loading state
    val loading = mutableStateOf(false)

    // check email and password text
    fun loginUser(navController: NavController) {
        viewModelScope.launch {
            when {
                email.value.length < 9 -> {
                    snackBarState.showSnackbar("Invalid Email")
                }
                password.value.length < 7 -> {
                    snackBarState.showSnackbar("Invalid Password")
                }
                else -> {
                    login(navController = navController)
                }
            }
        }
    }

    //login user to server and save auth token
    private suspend fun login(navController: NavController) {
        loading.value = true
        withContext(Dispatchers.IO) {
            val register = repository.loginUser(email.value, password.value)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (register.message == Register.Successful.message) {
                    // remove signin page from backstack
                    navController.navigate(Screens.Main.route) {
                        popUpTo(Screens.SignIn.route) {
                            inclusive = true
                        }
                    }
                } else {
                    snackBarState.showSnackbar(register.message)
                }
            }
        }
    }


}