package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.payment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.useCase.register.SavePaymentMethodUseCase
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.USER_INFO_FAMILY
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.USER_INFO_NAME
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.USER_INFO_PHONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PaymentViewModel
@Inject
constructor(
    private val savePaymentMethodUseCase: SavePaymentMethodUseCase,
) : ViewModel() {

    // payment method model
    val isPress = mutableStateOf("paypal")

    // save payment method to datastore
    fun savePaymentMethod(
        navController: NavController,
        name: String?,
        family: String?,
        phone: String?,
    ) {
        viewModelScope.launch {
            savePaymentMethodUseCase(isPress.value)
            // go to next page
            // send user information to upload profile page
            navController.currentBackStackEntry?.arguments?.putString(USER_INFO_NAME, name)
            navController.currentBackStackEntry?.arguments?.putString(USER_INFO_FAMILY, family)
            navController.currentBackStackEntry?.arguments?.putString(USER_INFO_PHONE, phone)
            navController.navigate(Screens.UploadProfile.route)
        }
    }

}