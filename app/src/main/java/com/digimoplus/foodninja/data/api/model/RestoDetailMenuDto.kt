package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class RestoDetailMenuDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("restaurant_name")
    val restaurantName: String
)