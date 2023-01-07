package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.domain.model.MenuDetailInfo
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    // get all items from basket table database
    suspend fun basketList(): Flow<DataState<List<Basket>>>

    // delete basket item from database
    suspend fun deleteBasketItem(id: Int)

    // update basket item count when changed
    suspend fun updateCount(id: Int, count: Int)

    // add menu item into basket
    suspend fun addNewItemToBasket(
        menuDetailInfo: MenuDetailInfo,
    ): Flow<DataState<Long>>

    // check if menu exist into basket and get count
    suspend fun getBasketItem(
        menuId: Int,
    ): Flow<DataState<Basket?>>

    //check is other restaurants in basket exist
    suspend fun isCurrentRestaurant(
        restaurantId: Int,
    ): Flow<DataState<Boolean>>

    //remove other restaurants from basket
    suspend fun deleteCurrentRestaurant(
        restaurantId: Int,
    )

    suspend fun getBasketItemsCount(): Int

}