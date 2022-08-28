package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.sign_up

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.domain.repository.SignUpRepository
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

    // snack bar state
    val snackBarState = SnackbarHostState()

    // name textField
    val name: MutableState<String> = mutableStateOf("")

    // email textField
    val email: MutableState<String> = mutableStateOf("")

    // password textFiled
    val password: MutableState<String> = mutableStateOf("")

    // loading ui state
    val loading: MutableState<Boolean> = mutableStateOf(false)

    // checkBox one state
    val checkOne: MutableState<Boolean> = mutableStateOf(false)

    // check box two state
    val checkTwo: MutableState<Boolean> = mutableStateOf(false)

    // check name & email & password & check one & check two
    fun register(navController: NavController) {
        viewModelScope.launch {
            when {
                name.value.length < 2 -> {
                    snackBarState.showSnackbar("The name must not be less than two letters")
                }
                email.value.length < 9 -> {
                    snackBarState.showSnackbar("Invalid Email")
                }
                password.value.length < 7 -> {
                    snackBarState.showSnackbar("Invalid Password")
                }
                /*!checkOne.value -> {
                    snackBarState.showSnackbar("Invalid checkOne")
                }
                !checkTwo.value -> {
                    snackBarState.showSnackbar("Invalid checkTwo")
                }*/
                else -> {
                    registerUser(navController)
                }
            }
        }
    }

    // register user to server 
    private suspend fun registerUser(navController: NavController) {
        loading.value = true
        withContext(Dispatchers.IO) {
            val register = repository.registerUser(name.value, email.value, password.value)
            withContext(Dispatchers.Main) {
                // update ui
                loading.value = false
                if (register.message == Register.Successful.message) {
                    // put name to user information page
                    navController.navigate(Screens.UserInformation.createRoute(name.value)) {
                        // remove sign up page from backstack
                        popUpTo(Screens.SignUp.route) {
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