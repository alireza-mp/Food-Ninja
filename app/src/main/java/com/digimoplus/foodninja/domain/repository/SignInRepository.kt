package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.Register

interface SignInRepository {

    // login user to server and get user auth token and user id
    suspend fun loginUser(email: String, password: String): Register

}