package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.location

import android.os.Bundle
import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.model.RegisterInfo
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
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
    private val repository: RegisterUserRepositoryImpl
) : ViewModel() {

    val selectedLocation = mutableStateOf(LatLng(34.64, 50.88))
    val mapIsVisible = mutableStateOf(false)
    val loading = mutableStateOf(false)
    val snackBarState = SnackbarHostState()

    fun saveLocation(bundle: Bundle, navController: NavController) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val registerInfo = bundle.getParcelable<RegisterInfo>("register")
            val location = "${selectedLocation.value.latitude}:${selectedLocation.value.longitude}"
            if (registerInfo != null) {
                val registerMessage = repository.addUserInformation(
                    name = registerInfo.name.toString(),
                    family = registerInfo.family.toString(),
                    phone = registerInfo.phone.toString(),
                    location = location
                )
                withContext(Dispatchers.Main) {
                    loading.value = false
                    snackBarState.showSnackbar(registerMessage.message)
                    navController.navigate(R.id.action_chooseLocationFragment_to_successNotificationFragment)
                }
            } else {
                withContext(Dispatchers.Main) {
                    snackBarState.showSnackbar(Register.SomeError.message)
                    loading.value = false
                }
            }
        }
    }

}