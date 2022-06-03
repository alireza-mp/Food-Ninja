package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant

interface HomeRepository {


    suspend fun getRestaurantList(token: String): DataState<List<Restaurant>>

    suspend fun restaurantSearch(
        token: String,
        search: String,
        page: Int,
    ): DataState<List<Restaurant>>

    suspend fun getAllRestaurantsList(token: String, page: Int): DataState<List<Restaurant>>

    suspend fun getMenuList(token: String): DataState<List<Menu>>

    suspend fun menuSearch(
        token: String,
        search: String,
        page: Int,
    ): DataState<List<Menu>>

    suspend fun getAllMenuList(token: String, page: Int): DataState<List<Menu>>

}