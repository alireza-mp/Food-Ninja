package com.digimoplus.foodninja.domain.model



data class MenuDetail(
    val menuDetailInfo: MenuDetailInfo,
    val menuDetailComments: List<MenuDetailComments>,
    val menuDetailMaterials: List<String>,
)