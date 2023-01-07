package com.digimoplus.foodninja.domain.repository.data_source_impl

import com.digimoplus.foodninja.data.api.RestaurantService
import com.digimoplus.foodninja.data.api.model.RestaurantDetailDto
import com.digimoplus.foodninja.data.api.model.RestaurantDto
import com.digimoplus.foodninja.data.api.model.RestaurantListDto
import com.digimoplus.foodninja.domain.repository.data_source.RestaurantRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RestaurantRemoteDataSourceImpl
@Inject
constructor(
    private val restaurantService: RestaurantService,
) : RestaurantRemoteDataSource {

    override suspend fun getNearestRestaurantsList(
        token: String,
    ): Response<List<RestaurantDto>>? {
        return try {
            restaurantService.nearestRestaurantsList(token)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRestaurantsList(
        token: String,
        page: Int,
    ): Response<RestaurantListDto>? {
        return try {
            restaurantService.restaurantsList(token, page)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun restaurantSearch(
        token: String,
        search: String,
        page: Int,
    ): Response<RestaurantListDto>? {
        return try {
            restaurantService.restaurantSearch(token, search, page)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRestaurantDetails(
        token: String,
        restaurantId: Int,
    ): Response<RestaurantDetailDto>? {
        return try {
            restaurantService.restaurantDetails(token, restaurantId)
        } catch (e: Exception) {
            null
        }
    }

}