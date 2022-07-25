package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class ResponseStateDto(
    @SerializedName("message")
    val message: String
)