package com.digimoplus.foodninja.domain.model


data class RestaurantDetailMenu(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: String,
    val restaurantId: Int,
    val restaurantName: String,
)