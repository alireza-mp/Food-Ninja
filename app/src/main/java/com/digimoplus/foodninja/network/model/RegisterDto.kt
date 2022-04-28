package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("message")
    val message: String
)