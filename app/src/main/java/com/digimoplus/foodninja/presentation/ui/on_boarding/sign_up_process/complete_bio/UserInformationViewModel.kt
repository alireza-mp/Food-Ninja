package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.UserInfo
import com.digimoplus.foodninja.presentation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel
@Inject
constructor() : ViewModel() {

    val loading = mutableStateOf(false)
    val name = mutableStateOf("")
    val family = mutableStateOf("")
    val phone = mutableStateOf("")

    fun addInfo(snackBarHost: SnackbarHostState, navController: NavController) {
        viewModelScope.launch {
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
                    val userInfo = UserInfo(name.value, family.value, phone.value)
                    // send object of user to payment page
                    navController.currentBackStackEntry?.arguments?.putParcelable("user",
                        userInfo)
                    navController.navigate(Screens.Payment.route)
                }
            }
        }
    }

}