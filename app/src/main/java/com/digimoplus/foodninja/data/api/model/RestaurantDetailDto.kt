package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class RestaurantDetailDto(
    @SerializedName("restaurant_detail_comments")
    val restoDetailCommentDtos: List<RestoDetailCommentDto>,
    @SerializedName("restaurant_detail_info")
    val restoDetailInfoDto: RestoDetailInfoDto,
    @SerializedName("restaurant_detail_menus")
    val restoDetailMenusDto: List<RestoDetailMenuDto>,
)