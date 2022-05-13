package com.digimoplus.foodninja.domain.model

data class Menu(
    val name: String,
    val id: Int,
    val restaurantId: Int,
    val restaurantName: String,
    val imageUrl: String,
    val price: String,
)