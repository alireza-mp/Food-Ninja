package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.upload_photo

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.repository.UploadPhotoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class UploadPhotoViewModel
@Inject
constructor(
    private val repository: UploadPhotoRepositoryImpl,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    val imageUrl = mutableStateOf("none")
    val snackBarState = SnackbarHostState()
    val loading = mutableStateOf(false)

    init {
        viewModelScope.launch {
            profileUrlIsExist()?.let {
                imageUrl.value = it
            }
        }
    }

    fun uploadProfile(context: Context, uri: Uri) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            var register: Register? = null
            val inputStream: InputStream? =
                context.contentResolver.openInputStream(uri)
            inputStream?.let {
                register = repository.uploadProfile(inputStream)
            }
            withContext(Dispatchers.Main) {
                loading.value = false
                if (register != null) {
                    snackBarState.showSnackbar(register?.message ?: Register.SomeError.message)
                    if (register?.message == Register.Successful.message) {
                        imageUrl.value = "${Constants.BASE_URL}${register?.value.toString()}"
                        saveProfileUrl(register?.value as String)
                    }
                } else {
                    snackBarState.showSnackbar(Register.SomeError.message)
                }
            }
        }
    }

    fun uploadProfile(bitmap: Bitmap) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(TAG, "uploadProfile: first of fun")
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bitmapData = bos.toByteArray()
            val inputStream = ByteArrayInputStream(bitmapData)
            // upload photo
            val register = repository.uploadProfile(inputStream)
            withContext(Dispatchers.Main) {
                snackBarState.showSnackbar(register.message)
                if (register == Register.Successful) {
                    saveProfileUrl(register.value as String)
                    imageUrl.value = "${Constants.BASE_URL}${register.value.toString()}"
                }
                loading.value = false
            }
        }
    }


    private suspend fun profileUrlIsExist(): String? {
        return dataStore.data.first()[PreferencesKeys.userProfileUrl]
    }

    private suspend fun saveProfileUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userProfileUrl] = "${Constants.BASE_URL}${url}"
        }
    }


}