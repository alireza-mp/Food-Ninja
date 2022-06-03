package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class MenuList(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<MenuDto>,
    @SerializedName("last_page")
    val lastPage: Int,
)