package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.RegisterState
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    // login user to server and get user auth token and user id
    suspend fun login(email: String, password: String): Flow<RegisterState<Nothing?>>

    suspend fun checkToken(): String

}