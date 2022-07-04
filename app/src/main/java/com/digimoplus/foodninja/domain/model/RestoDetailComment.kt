package com.digimoplus.foodninja.domain.model



data class RestoDetailComment(
    val date: String,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val rate: String,
    val restaurantId: Int
)