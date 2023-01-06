package com.digimoplus.foodninja.domain.model



data class RestoDetail(
    val restaurantDetailComment: List<RestaurantDetailComment>,
    val restoDetailInfo: RestoDetailInfo,
    val restaurantDetailMenus: List<RestaurantDetailMenu>
)