package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class MenuDetailInfoDto(
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_detail")
    val imageDetail: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("location_km")
    val locationKm: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("restaurant_name")
    val restaurantName: String,
    @SerializedName("title")
    val title: String
)