package com.digimoplus.foodninja.domain.model



data class RestoDetailMenu(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: String,
    val restaurantId: Int,
    val restaurantName: String
)