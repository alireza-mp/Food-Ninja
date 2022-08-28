package com.digimoplus.foodninja.data.api.model


import com.google.gson.annotations.SerializedName

data class MenuDetailDto(
    @SerializedName("menu_detail_info")
    val menuDetailInfoDto: MenuDetailInfoDto,
    @SerializedName("menu_detail_comments")
    val menuDetailCommentsDto: List<MenuDetailCommentsDto>,
    @SerializedName("menu_detail_materials")
    val menuDetailMaterialsDto: List<MenuDetailMaterialsDto>,
)