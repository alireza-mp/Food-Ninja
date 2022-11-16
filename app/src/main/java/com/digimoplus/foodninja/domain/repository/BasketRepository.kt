package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.domain.util.DataState

interface BasketRepository {

    // get all items from basket table database
    suspend fun basketList(): DataState<List<Basket>>

    // delete basket item from database
    suspend fun deleteBasketItem(id: Int)

    // update basket item count when changed
    suspend fun updateCount(id: Int, count: Int)


}