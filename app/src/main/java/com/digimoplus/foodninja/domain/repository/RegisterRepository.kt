package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.RegisterState
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface RegisterRepository {

    // register user to server and save auth token and user id
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<RegisterState<Nothing?>>

    // upload profile image to server and get profile image url
    suspend fun uploadProfileImage(inputStream: InputStream): Flow<RegisterState<String>>

    // save name & family & phone & location to server
    suspend fun completeRegister(
        name: String,
        family: String,
        phone: String,
        location: String,
    ): Flow<RegisterState<Nothing?>>

    suspend fun getCompleteUserRegister(): String

    suspend fun savePaymentMethod(payment: String)

    suspend fun saveIntroduction(state: Boolean)

    suspend fun checkIntroduction(): String

}