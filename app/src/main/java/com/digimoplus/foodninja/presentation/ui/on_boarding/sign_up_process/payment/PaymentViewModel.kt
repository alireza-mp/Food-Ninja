package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.payment

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.USER_INFO_FAMILY
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.USER_INFO_NAME
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.USER_INFO_PHONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PaymentViewModel
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
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

            dataStore.edit { preferences ->
                preferences[PreferencesKeys.userPaymentMethod] = isPress.value
            }

            // go to next page
            // send user information to upload profile page
            navController.currentBackStackEntry?.arguments?.putString(USER_INFO_NAME, name)
            navController.currentBackStackEntry?.arguments?.putString(USER_INFO_FAMILY, family)
            navController.currentBackStackEntry?.arguments?.putString(USER_INFO_PHONE, phone)
            navController.navigate(Screens.UploadProfile.route)
        }
    }

}