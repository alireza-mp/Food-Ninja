package com.digimoplus.foodninja.persistence

import androidx.room.*
import com.digimoplus.foodninja.persistence.model.BasketTable

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket_tb  ")
    suspend fun basketList(): List<BasketTable>

    @Query("DELETE  FROM basket_tb  WHERE id = :id ")
    suspend fun deleteBasketItem(id: Int)

    @Query("UPDATE basket_tb SET count =:count WHERE id = :id")
    suspend fun updateCountBasketItem(id: Int, count: Int)

}