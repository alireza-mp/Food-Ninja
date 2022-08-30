package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface HomeRepository {


    suspend fun getRestaurantList(token: String): Flow<DataState<List<Restaurant>>>

    suspend fun restaurantSearch(
        token: String,
        search: String,
        page: Int,
    ): DataState<List<Restaurant>>

    suspend fun getAllRestaurantsList(token: String, page: Int): DataState<List<Restaurant>>

    suspend fun getMenuList(token: String): Flow<DataState<List<Menu>>>

    suspend fun menuSearch(
        token: String,
        search: String,
        page: Int,
    ): DataState<List<Menu>>

    suspend fun getAllMenuList(token: String, page: Int): DataState<List<Menu>>

}