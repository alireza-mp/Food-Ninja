package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.location

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.domain.repository.RegisterUserRepository
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
    private val repository: RegisterUserRepository,
) : ViewModel() {

    //  selected map location
    val selectedLocation = mutableStateOf(LatLng(34.64, 50.88))

    // map ui state
    val mapIsVisible = mutableStateOf(false)

    // loading ui state
    val loading = mutableStateOf(false)

    // snack bar state
    val snackBarState = SnackbarHostState()

    // save location and received user information to server
    fun saveLocation(name: String?, family: String?, phone: String?, navController: NavController) {
        // update ui
        loading.value = true

        viewModelScope.launch(Dispatchers.IO) {

            val location = "${selectedLocation.value.latitude}:${selectedLocation.value.longitude}"

            // save location
            val registerMessage = repository.addUserInformation(
                name = name ?: "null",
                family = family ?: "null",
                phone = phone ?: "null",
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
        }
    }

}