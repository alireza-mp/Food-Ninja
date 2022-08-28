package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.Register

interface SignUpRepository {

    // register user to server and save auth token and user id
    suspend fun registerUser(name: String, email: String, password: String): Register

}