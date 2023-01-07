package com.digimoplus.foodninja.domain.model

data class RestaurantList(
    val currentPage: Int,
    val lastPage: Int,
    val data: List<Restaurant>,
)