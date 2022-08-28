package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class RestaurantList(
    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("last_page")
    val lastPage: Int,

    @SerializedName("data")
    val data: List<RestaurantDto>,
)