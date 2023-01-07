package com.digimoplus.foodninja.domain.model


data class MenuList(
    val currentPage: Int,
    val data: List<Menu>,
    val lastPage: Int,
)