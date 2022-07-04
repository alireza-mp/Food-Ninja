package com.digimoplus.foodninja.repository

import androidx.datastore.core.DataStore
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.RestoDetail
import com.digimoplus.foodninja.network.AuthService
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.network.model.RestoDetailDtoMapper
import javax.inject.Inject

class RestoDetailRepositoryImpl
@Inject constructor(
    private val authService: AuthService,
    private val mapper: RestoDetailDtoMapper,
) : RestoDetailRepository {

    override suspend fun getDetails(token: String, restaurantId: Int): DataState<RestoDetail> {
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