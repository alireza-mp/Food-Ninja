package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class MenuDetailMaterialsDto(
    @SerializedName("name")
    val name: String
)