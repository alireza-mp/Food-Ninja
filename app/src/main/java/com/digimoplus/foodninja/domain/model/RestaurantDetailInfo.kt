package com.digimoplus.foodninja.domain.model



data class RestoDetailInfo(
    val desc: String,
    val id: Int,
    val imageUrl: String,
    val imgDetail: String,
    val location: String,
    val locationKm: String,
    val name: String,
    val rate: String,
    val title: String
)