package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class RestoDetailDto(
    @SerializedName("restaurant_detail_comments")
    val restoDetailCommentDtos: List<RestoDetailCommentDto>,
    @SerializedName("restaurant_detail_info")
    val restoDetailInfoDto: RestoDetailInfoDto,
    @SerializedName("restaurant_detail_menus")
    val restoDetailMenusDto: List<RestoDetailMenuDto>
)