package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("message")
    val message: String,
)