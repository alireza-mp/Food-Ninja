package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Register

interface SignUpRepository {

    suspend fun registerUser(name: String, email: String, password: String): Register

}