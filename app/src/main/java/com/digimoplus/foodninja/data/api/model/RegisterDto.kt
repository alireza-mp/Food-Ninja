package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String
)