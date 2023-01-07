package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.upload_profile

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.useCase.profile.CheckProfileImageUrlUseCase
import com.digimoplus.foodninja.domain.useCase.register.UploadProfileImageUseCase
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.domain.util.toInputStream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UploadProfilePhotoViewModel
@Inject
constructor(
    private val checkProfileImageUrlUseCase: CheckProfileImageUrlUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
) : ViewModel() {

    // profile image url
    val imageUrl = mutableStateOf("null")

    // snack bar state
    val snackBarState = SnackbarHostState()

    // loading ui state
    var uiState by mutableStateOf(UiState.Visible)
        private set

    init {
        checkProfileImageExist()
    }


    // upload profile image to server from gallery
    fun uploadProfile(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val inputStream = context.contentResolver.openInputStream(uri)
            withContext(Dispatchers.Main) {
                inputStream ?: snackBarState.showSnackbar(RegisterState.SomeError.message)
            }
            inputStream?.let {
                uploadProfileImageUseCase(it).onEach { result ->
                    when (result) {
                        is RegisterState.Loading -> {
                            uiState = UiState.Loading
                        }
                        is RegisterState.Successful -> {
                            uiState = UiState.Visible
                            imageUrl.value = result.data
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

    // upload profile image to server from camera
    fun uploadProfile(bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            val inputStream = bitmap.toInputStream()
            uploadProfileImageUseCase(inputStream).onEach { result ->
                when (result) {
                    is RegisterState.Loading -> {
                        uiState = UiState.Loading
                    }
                    is RegisterState.Successful -> {
                        uiState = UiState.Visible
                        imageUrl.value = result.data
                    }
                    else -> {
                        uiState = UiState.NoInternet
                        snackBarState.showSnackbar(result.message)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun checkProfileImageExist() {
        viewModelScope.launch {
            imageUrl.value = checkProfileImageUrlUseCase()
        }
    }

}