package com.digimoplus.foodninja.domain.repository

interface ProfileRepository {

    suspend fun getProfileImageUrl(): String

    suspend fun saveProfileImageUrl(url: String)

}