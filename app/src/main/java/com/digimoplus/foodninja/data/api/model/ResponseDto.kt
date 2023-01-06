package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class ResponseStateDto(
    @SerializedName("message")
    val message: String
)