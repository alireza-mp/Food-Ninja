package com.digimoplus.foodninja.domain.repository.data_source

import com.digimoplus.foodninja.data.api.model.RestaurantDetailDto
import com.digimoplus.foodninja.data.api.model.RestaurantDto
import com.digimoplus.foodninja.data.api.model.RestaurantListDto
import retrofit2.Response


interface RestaurantRemoteDataSource {

    suspend fun getNearestRestaurantsList(token: String): Response<List<RestaurantDto>>?

    suspend fun getRestaurantsList(token: String, page: Int): Response<RestaurantListDto>?

    suspend fun restaurantSearch(
        token: String,
        search: String,
        page: Int,
    ): Response<RestaurantListDto>?

    suspend fun getRestaurantDetails(
        token: String,
        restaurantId: Int,
    ): Response<RestaurantDetailDto>?

}