package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class RestoDetailInfoDto(
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("img_detail")
    val imgDetail: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("location_km")
    val locationKm: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("title")
    val title: String
)