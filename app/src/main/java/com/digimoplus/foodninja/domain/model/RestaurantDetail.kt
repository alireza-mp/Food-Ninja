package com.digimoplus.foodninja.domain.model


data class RestaurantDetail(
    val restaurantDetailComment: List<RestaurantDetailComment>,
    val restaurantDetailInfo: RestaurantDetailInfo,
    val restaurantDetailMenus: List<RestaurantDetailMenu>,
)