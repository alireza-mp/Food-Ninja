package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import android.os.Bundle
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.model.RegisterInfo
import com.digimoplus.foodninja.repository.RegisterUserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel
@Inject
constructor() : ViewModel() {

    val loading = mutableStateOf(false)
    val name = mutableStateOf("")
    val family = mutableStateOf("")
    val phone = mutableStateOf("")

    fun addInfo(snackBarHost: SnackbarHostState, navController: NavController) {
        viewModelScope.launch {
            when {
                name.value.length < 5 -> {
                    snackBarHost.showSnackbar("name")
                }
                family.value.length < 5 -> {
                    snackBarHost.showSnackbar("family")
                }
                phone.value.length < 11 -> {
                    snackBarHost.showSnackbar("phone")
                }
                else -> {
                    val registerInfo = RegisterInfo(name.value, family.value, phone.value)
                    val bundle = Bundle()
                    bundle.putParcelable("register", registerInfo)
                    navController.navigate(R.id.action_userInformationFragment_to_paymentFragment,bundle)
                }
            }
        }
    }

}