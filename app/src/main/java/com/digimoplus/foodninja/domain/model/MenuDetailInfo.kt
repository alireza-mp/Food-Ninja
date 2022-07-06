package com.digimoplus.foodninja.domain.model

import com.google.gson.annotations.SerializedName

data class MenuDetailInfo(
    val descriptionTop: String,
    val descriptionBottom: String,
    val id: Int,
    val imageDetail: String,
    val imageUrl: String,
    val locationKm: String,
    val name: String,
    val price: String,
    val rate: String,
    val restaurantId: Int,
    val restaurantName: String,
    val title: String
)