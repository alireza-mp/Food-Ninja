package com.digimoplus.foodninja.domain.repository.data_source_impl

import com.digimoplus.foodninja.data.api.RegisterService
import com.digimoplus.foodninja.data.api.model.RegisterDto
import com.digimoplus.foodninja.data.api.model.ResponseDto
import com.digimoplus.foodninja.domain.repository.data_source.RegisterRemoteDataSource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.InputStream
import javax.inject.Inject

class RegisterRemoteDataSourceImpl
@Inject
constructor(
    private val registerService: RegisterService,
) : RegisterRemoteDataSource {

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Response<RegisterDto>? {
        return try {
            registerService.registerUser(name, email, password, password)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun uploadProfileImage(
        id: String,
        inputStream: InputStream,
    ): Response<ResponseDto>? {
        return try {

            val partRequest = MultipartBody.Part.createFormData(
                "image",
                "profile_$id.jpeg",
                inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
            )
            val idRequest: RequestBody =
                id.toRequestBody("text/*".toMediaTypeOrNull())

            registerService.uploadUserProfile(partRequest, idRequest)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun completeUserRegister(
        id: Int,
        name: String,
        family: String,
        phone: String,
        location: String,
    ): Response<ResponseDto>? {
        return try {
            registerService.completeUserRegister(id, name, family, phone, location)
        } catch (e: Exception) {
            null
        }
    }

}