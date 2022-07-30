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

    // snack bar state
    val snackBarState = SnackbarHostState()

    // loading ui state
    val loading = mutableStateOf(false)

    // name textField
    val name = mutableStateOf("")

    // family textField
    val family = mutableStateOf("")

    // phone textField
    val phone = mutableStateOf("")

    //check name & family & phone
    fun addInfo(navController: NavController) {
        viewModelScope.launch {
            when {
                name.value.length < 5 -> {
                    snackBarState.showSnackbar("name")
                }
                family.value.length < 5 -> {
                    snackBarState.showSnackbar("family")
                }
                phone.value.length < 11 -> {
                    snackBarState.showSnackbar("phone")
                }
                else -> {
                    // save data to model
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