package com.digimoplus.foodninja.data.api

import com.digimoplus.foodninja.data.api.model.RestaurantDetailDto
import com.digimoplus.foodninja.data.api.model.RestaurantDto
import com.digimoplus.foodninja.data.api.model.RestaurantListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestaurantService {

    @GET("restaurant")
    suspend fun nearestRestaurantsList(
        @Header("Authorization") token: String,
    ): Response<List<RestaurantDto>>

    @GET("restaurant_all")
    suspend fun restaurantsList(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
    ): Response<RestaurantListDto>

    @GET("restaurant_search")
    suspend fun restaurantSearch(
        @Header("Authorization") token: String,
        @Query("search") search: String,
        @Query("page") page: Int,
    ): Response<RestaurantListDto>

    @GET("restaurant_detail")
    suspend fun restaurantDetails(
        @Header("Authorization") token: String,
        @Query("restaurant_id") id: Int,
    ): Response<RestaurantDetailDto>

}