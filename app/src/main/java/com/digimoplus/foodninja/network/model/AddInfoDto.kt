package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class AddInfoDto(
    @SerializedName("message")
    val message: String
)