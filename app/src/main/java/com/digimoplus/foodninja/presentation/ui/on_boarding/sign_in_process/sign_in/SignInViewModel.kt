package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_in_process.sign_in

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.Screens
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
    private val repository: SignInRepositoryImpl,
) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val loading = mutableStateOf(false)


    fun loginUser(state: SnackbarHostState, navController: NavController) {
        viewModelScope.launch {
            when {
                email.value.length < 9 -> {
                    state.showSnackbar("Invalid Email")
                }
                password.value.length < 7 -> {
                    state.showSnackbar("Invalid Password")
                }
                else -> {
                    login(navController = navController, snackBarState = state)
                }
            }
        }
    }


    private suspend fun login(navController: NavController,snackBarState: SnackbarHostState) {
        loading.value = true
        withContext(Dispatchers.IO) {
            val register = repository.loginUser(email.value, password.value)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (register.message == Register.Successful.message){
                    // remove signin page from backstack
                    navController.navigate(Screens.Main.route) {
                        popUpTo(Screens.SignIn.route) {
                            inclusive = true
                        }
                    }
                }else{
                    snackBarState.showSnackbar(register.message)
                }
            }
        }
    }


}