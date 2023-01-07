package com.digimoplus.foodninja.domain.repository.data_source

import com.digimoplus.foodninja.data.db.model.BasketTable

interface BasketLocalDataSource {

    suspend fun basketList(userId: Int): List<BasketTable>?

    suspend fun deleteBasketItem(id: Int)

    suspend fun updateCount(id: Int, count: Int)

    suspend fun addNewItemToBasket(
        basketTable: BasketTable,
    ): Long?

    suspend fun getItemCount(
        userId: Int,
        menuId: Int,
    ): BasketTable?

    suspend fun isCurrentRestaurant(
        restaurantId: Int,
    ): Boolean?

    suspend fun deleteCurrentRestaurant(
        restaurantId: Int,
    )

    suspend fun getItemsCount(userId: Int): Int

}