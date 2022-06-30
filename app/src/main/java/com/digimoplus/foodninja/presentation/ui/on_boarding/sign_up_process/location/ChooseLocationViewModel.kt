package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.location

import android.os.Bundle
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.model.UserInfo
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.repository.RegisterUserRepositoryImpl
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChooseLocationViewModel
@Inject
constructor(
    private val repository: RegisterUserRepositoryImpl,
) : ViewModel() {

    val selectedLocation = mutableStateOf(LatLng(34.64, 50.88))
    val mapIsVisible = mutableStateOf(false)
    val loading = mutableStateOf(false)
    val snackBarState = SnackbarHostState()

    fun saveLocation(userInfo: UserInfo?, navController: NavController) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val location = "${selectedLocation.value.latitude}:${selectedLocation.value.longitude}"

            if (userInfo != null) {
                val registerMessage = repository.addUserInformation(
                    name = userInfo.name.toString(),
                    family = userInfo.family.toString(),
                    phone = userInfo.phone.toString(),
                    location = location
                )
                loading.value = false
                // if successful added
                if (registerMessage == Register.Successful) {
                    withContext(Dispatchers.Main) {
                        // navigate to success page
                        navController.navigate(Screens.SuccessPage.route) {
                            //remove all lasts pages form backstack
                            popUpTo(0)
                        }
                    }
                } else {
                    snackBarState.showSnackbar(registerMessage.message)
                }
            } else {
                snackBarState.showSnackbar(Register.SomeError.message)
                loading.value = false
            }
        }
    }

}