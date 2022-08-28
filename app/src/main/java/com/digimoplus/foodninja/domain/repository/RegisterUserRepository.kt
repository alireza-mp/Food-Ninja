package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.Register

interface RegisterUserRepository {

    // save name & family & phone & location to server
    suspend fun addUserInformation(
        name: String,
        family: String,
        phone: String,
        location: String,
    ): Register

}