package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class RestoDetailCommentDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int
)