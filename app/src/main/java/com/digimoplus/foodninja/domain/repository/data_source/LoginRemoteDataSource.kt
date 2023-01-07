package com.digimoplus.foodninja.domain.repository.data_source

import com.digimoplus.foodninja.data.api.model.RegisterDto
import retrofit2.Response

interface LoginRemoteDataSource {

    suspend fun login(email: String, password: String): Response<RegisterDto>?

}