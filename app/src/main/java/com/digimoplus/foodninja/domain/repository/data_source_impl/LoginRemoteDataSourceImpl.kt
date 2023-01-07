package com.digimoplus.foodninja.domain.repository.data_source_impl

import com.digimoplus.foodninja.data.api.LoginService
import com.digimoplus.foodninja.data.api.model.RegisterDto
import com.digimoplus.foodninja.domain.repository.data_source.LoginRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteDataSourceImpl
@Inject
constructor(
    private val loginService: LoginService,
) : LoginRemoteDataSource {

    override suspend fun login(email: String, password: String): Response<RegisterDto>? {
        return try {
            loginService.login(email, password)
        } catch (e: Exception) {
            null
        }
    }

}