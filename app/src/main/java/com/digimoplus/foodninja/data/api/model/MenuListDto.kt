package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class MenuListDto(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<MenuDto>,
    @SerializedName("last_page")
    val lastPage: Int,
)