package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.model.RestaurantDetail
import com.digimoplus.foodninja.domain.model.RestaurantList
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getNearestRestaurantList(): Flow<DataState<List<Restaurant>>>

    suspend fun getRestaurantsList(page: Int): Flow<DataState<RestaurantList>>

    suspend fun restaurantSearch(
        search: String,
        page: Int,
    ): Flow<DataState<RestaurantList>>


    suspend fun getRestaurantDetails(restaurantId: Int): Flow<DataState<RestaurantDetail>>
}