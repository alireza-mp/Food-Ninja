package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class MenuDetailCommentsDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("menu_id")
    val menuId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rate")
    val rate: String
)