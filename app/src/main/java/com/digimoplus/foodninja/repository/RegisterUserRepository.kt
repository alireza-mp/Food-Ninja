package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Register

interface RegisterUserRepository {
    suspend fun addUserInformation(name: String, family: String, phone: String,location:String): Register
}