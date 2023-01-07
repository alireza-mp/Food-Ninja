package com.digimoplus.foodninja.domain.repository.data_source

import com.digimoplus.foodninja.data.api.model.RegisterDto
import com.digimoplus.foodninja.data.api.model.ResponseDto
import retrofit2.Response
import java.io.InputStream

interface RegisterRemoteDataSource {

    suspend fun registerUser(name: String, email: String, password: String): Response<RegisterDto>?

    suspend fun uploadProfileImage(
        id: String,
        inputStream: InputStream,
    ): Response<ResponseDto>?

    suspend fun completeUserRegister(
        id: Int,
        name: String,
        family: String,
        phone: String,
        location: String,
    ): Response<ResponseDto>?


}