package com.digimoplus.foodninja.domain.model


data class MenuDetailComments(
    val date: String,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val menuId: Int,
    val name: String,
    val rate: String
)