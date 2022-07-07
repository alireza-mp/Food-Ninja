package com.digimoplus.foodninja.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.digimoplus.foodninja.persistence.model.BasketTable

@Dao
interface ProductDao {

    @Insert(entity = BasketTable::class, onConflict = REPLACE)
    suspend fun addToBasket(basketTable: BasketTable): Long

    @Query("SELECT * FROM basket_tb WHERE user_id = :userId AND menu_id = :menuId")
    suspend fun checkIsExist(userId: Int, menuId: Int): List<BasketTable>

    @Query("DELETE FROM basket_tb WHERE user_id =  :userId AND menu_id = :menuId")
    suspend fun deleteBasketItem(userId: Int, menuId: Int)
}