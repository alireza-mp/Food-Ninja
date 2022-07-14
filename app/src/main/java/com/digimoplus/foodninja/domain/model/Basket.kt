package com.digimoplus.foodninja.domain.model


data class Basket(

    var id: Int,
    var userId: Int,
    var restoId: Int,
    val menuId: Int,
    var count: Int,
    val name: String,
    val restoName: String,
    val price: String,
    val imageUrl: String,

    )
