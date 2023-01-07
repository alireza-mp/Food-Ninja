package com.digimoplus.foodninja.data.db

import androidx.room.*
import com.digimoplus.foodninja.data.db.model.BasketTable

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket_tb  WHERE user_id = :userId")
    suspend fun basketList(userId: Int): List<BasketTable>

    @Query("DELETE  FROM basket_tb  WHERE id = :id ")
    suspend fun deleteBasketItem(id: Int)

    @Query("UPDATE basket_tb SET count =:count WHERE id = :id")
    suspend fun updateCount(id: Int, count: Int)

}