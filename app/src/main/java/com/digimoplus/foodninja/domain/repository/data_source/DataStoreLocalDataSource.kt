package com.digimoplus.foodninja.domain.repository.data_source

interface DataStoreLocalDataSource {
    suspend fun getToken(): String
    suspend fun getUserId(): Int
    suspend fun getCompleteRegister(): String
    suspend fun getUserIntroduction(): String
    suspend fun getUserProfileUrl(): String
    suspend fun getUserPaymentMethod(): String
    suspend fun saveUserId(id: Int?)
    suspend fun saveToken(token: String)
    suspend fun saveCompleteRegister(state: Boolean)
    suspend fun saveUserProfileUrl(url: String)
    suspend fun saveUserPaymentMethod(payment: String)
    suspend fun saveIntroduction(state: Boolean)
}