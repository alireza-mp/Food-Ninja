package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val USER_INFO_NAME = "name";
const val USER_INFO_FAMILY = "family";
const val USER_INFO_PHONE = "phone";

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
                name.value.length < 2 -> {
                    snackBarState.showSnackbar("The name must not be less than two letters")
                }
                family.value.length <2 -> {
                    snackBarState.showSnackbar("The family must not be less than two letters")
                }
                phone.value.length < 11 -> {
                    snackBarState.showSnackbar("TThe phone number must not be less than eleven digits")
                }
                else -> {

                    // send  user info to payment page
                    navController.currentBackStackEntry?.arguments?.putString(USER_INFO_NAME,
                        name.value)
                    navController.currentBackStackEntry?.arguments?.putString(USER_INFO_FAMILY,
                        family.value)
                    navController.currentBackStackEntry?.arguments?.putString(USER_INFO_PHONE,
                        phone.value)
                    navController.navigate(Screens.Payment.route)
                }
            }
        }
    }

}