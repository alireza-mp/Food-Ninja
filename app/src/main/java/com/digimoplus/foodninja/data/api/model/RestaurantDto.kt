package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class RestaurantDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
)