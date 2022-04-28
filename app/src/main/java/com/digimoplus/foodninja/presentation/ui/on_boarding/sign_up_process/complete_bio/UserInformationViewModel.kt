package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.repository.UserInfoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel
@Inject
constructor(
    private val repository: UserInfoRepositoryImpl
) : ViewModel() {

    val loading = mutableStateOf(false)
    val name = mutableStateOf("")
    val family = mutableStateOf("")
    val phone = mutableStateOf("")

    suspend fun addInfo( snackBarHost: SnackbarHostState, navController: NavController) {
        when {
            name.value.length < 5 -> {
                snackBarHost.showSnackbar("name")
            }
            family.value.length < 5 -> {
                snackBarHost.showSnackbar("family")
            }
            phone.value.length < 11 -> {
                snackBarHost.showSnackbar("phone")
            }
            else -> {
                addUserInfo(snackBarHost, navController)
            }
        }
    }

    private fun addUserInfo(
        snackBarHost: SnackbarHostState,
        navController: NavController
    ) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val register = repository.addUserInformation(name.value, family.value, phone.value)
            withContext(Dispatchers.Main) {
                loading.value = false
                snackBarHost.showSnackbar(register.message)
                if (register.message == Register.Successful.message) {
                    navController.navigate(R.id.action_userInformationFragment_to_paymentFragment)
                }
            }
        }
    }


}