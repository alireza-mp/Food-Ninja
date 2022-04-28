package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Register

interface UserInfoRepository {
    suspend fun addUserInformation(name: String, family: String, phone: String): Register
}