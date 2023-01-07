package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val USER_INFO_NAME = "name"
const val USER_INFO_FAMILY = "family"
const val USER_INFO_PHONE = "phone"

@HiltViewModel
class CompleteRegisterViewModel
@Inject
constructor(

) : ViewModel() {

    val snackBarState = SnackbarHostState()

    // name textField
    val name = mutableStateOf("")

    // family textField
    val family = mutableStateOf("")

    // phone textField
    val phone = mutableStateOf("")

    //check name & family & phone
    fun navigateToPaymentPage(navController: NavController) {
        viewModelScope.launch {
            when {
                name.value.isEmpty() -> {
                    snackBarState.showSnackbar("Name not entered!")
                }
                family.value.isEmpty() -> {
                    snackBarState.showSnackbar("Family not entered!")
                }
                phone.value.length < 11 -> {
                    snackBarState.showSnackbar("TThe phone number must not be less than eleven digits")
                }
                else -> {
                    navigateToPayment(navController, name.value, family.value, phone.value)
                }
            }
        }
    }

    private fun navigateToPayment(
        navController: NavController,
        name: String,
        family: String,
        phone: String,
    ) {
        navController.currentBackStackEntry?.arguments?.putString(
            USER_INFO_NAME,
            name,
        )
        navController.currentBackStackEntry?.arguments?.putString(
            USER_INFO_FAMILY,
            family,
        )
        navController.currentBackStackEntry?.arguments?.putString(
            USER_INFO_PHONE,
            phone,
        )
        navController.navigate(Screens.Payment.route)
        // send  user info to payment page

    }

}