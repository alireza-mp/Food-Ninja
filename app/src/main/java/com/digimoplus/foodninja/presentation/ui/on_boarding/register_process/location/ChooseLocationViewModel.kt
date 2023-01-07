package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.location

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.useCase.register.CompleteRegisterUseCase
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseLocationViewModel
@Inject
constructor(
    private val completeRegisterUseCase: CompleteRegisterUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(UiState.Visible)
        private set

    val snackBarState = SnackbarHostState()

    //  selected map location
    val selectedLocation = mutableStateOf(LatLng(34.64, 50.88))

    // map ui state
    val mapIsVisible = mutableStateOf(false)

    // save location and received user information to server
    fun saveLocation(name: String?, family: String?, phone: String?, navController: NavController) {
        val location = "${selectedLocation.value.latitude}:${selectedLocation.value.longitude}"
        viewModelScope.launch(Dispatchers.IO) {
            completeRegisterUseCase(name.toString(), family.toString(), phone.toString(), location)
                .onEach { result ->
                    when (result) {
                        is RegisterState.Loading -> {
                            uiState = UiState.Loading
                        }
                        is RegisterState.Successful -> {
                            uiState = UiState.Visible
                            navController.navigate(Screens.Success.route) {
                                //remove all lasts pages form backstack
                                popUpTo(0)
                            }
                        }
                        else -> {
                            uiState = UiState.NoInternet
                            snackBarState.showSnackbar(result.message)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}