package com.digimoplus.foodninja.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.network.model.MessageDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.InputStream
import javax.inject.Inject


class UploadPhotoRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>
) : UploadPhotoRepository {

    override suspend fun uploadProfile(inputStream: InputStream): Register {
        try {
            val response = uploadUserProfile(inputStream)
            return when (response.code()) {
                422 -> {
                    Register.InvalidError
                }
                200 -> {
                    val register = Register.Successful
                    register.value = response.body()?.message
                    register
                }
                else -> {
                    Register.SomeError
                }
            }
        } catch (e: Exception) {
            return Register.NetworkError
        }
    }

    private suspend fun getId(): String {
        return dataStore.data.first()[PreferencesKeys.userId].toString()
    }

    private suspend fun uploadUserProfile(inputStream: InputStream): Response<MessageDto> {
        val id = getId()
        val partRequest = MultipartBody.Part.createFormData(
            "image",
            "profile_$id.jpeg",
            inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
        )
        val idRequest: RequestBody =
            id.toRequestBody("text/*".toMediaTypeOrNull())

        return authService.uploadUserProfile(partRequest, idRequest)
    }
}