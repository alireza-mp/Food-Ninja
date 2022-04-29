package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.payment

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PaymentViewModel
@Inject
constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    val isPress = mutableStateOf("paypal")
    fun savePaymentMethod(navController: NavController) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.userPaymentMethod] = isPress.value
            }
            navController.navigate(R.id.action_paymentFragment_to_uploadPhotoFragment)
        }
    }

}