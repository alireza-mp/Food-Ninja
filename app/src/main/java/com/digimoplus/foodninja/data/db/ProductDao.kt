package com.digimoplus.foodninja.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.digimoplus.foodninja.data.db.model.BasketTable

@Dao
interface ProductDao {

    @Insert(entity = BasketTable::class, onConflict = REPLACE)
    suspend fun addToBasket(basketTable: BasketTable): Long

    @Query("SELECT * FROM basket_tb WHERE user_id = :userId AND menu_id = :menuId")
    suspend fun getItemCount(userId: Int, menuId: Int): List<BasketTable>

    @Query("DELETE FROM basket_tb WHERE user_id =  :userId AND menu_id = :menuId")
    suspend fun deleteBasketItem(userId: Int, menuId: Int)

    @Query("SELECT id FROM basket_tb WHERE restaurant_id != :restaurantId")
    suspend fun isCurrentRestaurant(restaurantId: Int): List<Int>

    @Query("DELETE FROM basket_tb WHERE restaurant_id != :restaurantId")
    suspend fun deleteCurrentRestaurant(restaurantId: Int)

}