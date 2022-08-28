package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.RestoDetail
import com.digimoplus.foodninja.domain.repository.RestoDetailRepository
import com.digimoplus.foodninja.data.api.AuthService
import com.digimoplus.foodninja.data.api.model.RestoDetailDtoMapper
import javax.inject.Inject

class RestoDetailRepositoryImpl
@Inject constructor(
    private val authService: AuthService,
    private val mapper: RestoDetailDtoMapper,
) : RestoDetailRepository {

    override suspend fun getRestaurantDetails(
        token: String,
        restaurantId: Int,
    ): DataState<RestoDetail> {
        return try {
            val result = authService.restaurantDetails(token = token, id = restaurantId)
            when (result.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    val data = result.body()
                    if (data != null) {
                        DataState.Success(
                            mapper.mapToDomainModel(data)
                        )
                    } else {
                        DataState.SomeError()
                    }
                }
                else -> {
                    DataState.SomeError()
                }
            }
        } catch (e: Exception) {
            DataState.NetworkError()
        }
    }
}