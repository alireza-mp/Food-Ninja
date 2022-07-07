package com.digimoplus.foodninja.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "basket_tb")
data class BasketTable(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "user_id")
    var userId: Int,
    @ColumnInfo(name = "menu_id")
    val menuId: Int,
    @ColumnInfo(name = "count")
    var count: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "restaurant_name")
    val restoName: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    )