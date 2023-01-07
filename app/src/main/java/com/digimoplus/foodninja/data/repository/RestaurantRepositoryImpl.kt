package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.data.api.model.RestaurantDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDtoMapper
import com.digimoplus.foodninja.domain.model.RestaurantDetail
import com.digimoplus.foodninja.domain.model.RestaurantList
import com.digimoplus.foodninja.domain.repository.RestaurantRepository
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source.RestaurantRemoteDataSource
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RestaurantRepositoryImpl
@Inject
constructor(
    private val restaurantRemoteDataSource: RestaurantRemoteDataSource,
    private val dataStore: DataStoreLocalDataSource,
    private val restaurantMapper: RestaurantDtoMapper,
    private val restaurantDetailsMapper: RestaurantDetailDtoMapper,
) : RestaurantRepository {

    //get restaurants list : main content
    override suspend fun getNearestRestaurantList() = flow {
        emit(DataState.Loading)
        val results = restaurantRemoteDataSource.getNearestRestaurantsList(dataStore.getToken())
        results ?: emit(DataState.NetworkError())
        results?.let {
            when (results.code()) {
                200 -> {
                    results.body() ?: emit(DataState.SomeError())
                    results.body()?.let {
                        emit(DataState.Success(
                            restaurantMapper.mapToDomainList(it)
                        ))
                    }
                }
                else -> {
                    emit(DataState.SomeError())
                }
            }
        }

    }

    // search in restaurants  : restaurant content
    override suspend fun restaurantSearch(
        search: String,
        page: Int,
    ): Flow<DataState<RestaurantList>> = flow {
        emit(DataState.Loading)
        val results =
            restaurantRemoteDataSource.restaurantSearch(dataStore.getToken(), search, page)
        results ?: emit(DataState.NetworkError())
        results?.let {
            when (results.code()) {
                200 -> {
                    results.body() ?: emit(DataState.SomeError())
                    results.body()?.let {
                        emit(DataState.Success(
                            RestaurantList(
                                currentPage = it.currentPage,
                                lastPage = it.lastPage,
                                data = restaurantMapper.mapToDomainList(it.data)
                            )
                        ))
                    }
                }
                else -> {
                    emit(DataState.SomeError())
                }
            }
        }

    }

    //get all restaurants list with page : restaurant content
    override suspend fun getRestaurantsList(
        page: Int,
    ): Flow<DataState<RestaurantList>> = flow {
        emit(DataState.Loading)
        val results = restaurantRemoteDataSource.getRestaurantsList(dataStore.getToken(), page)
        results ?: emit(DataState.NetworkError())
        results?.let {
            when (results.code()) {
                200 -> {
                    results.body() ?: emit(DataState.SomeError())
                    results.body()?.let {
                        emit(DataState.Success(
                            RestaurantList(
                                currentPage = it.currentPage,
                                lastPage = it.lastPage,
                                data = restaurantMapper.mapToDomainList(it.data)
                            )
                        ))
                    }
                }
                else -> {
                    emit(DataState.SomeError())
                }
            }
        }
    }

    // get restaurant details by restaurant id  : restaurant detail page
    override suspend fun getRestaurantDetails(
        restaurantId: Int,
    ): Flow<DataState<RestaurantDetail>> = flow {
        emit(DataState.Loading)
        val result =
            restaurantRemoteDataSource.getRestaurantDetails(dataStore.getToken(), restaurantId)
        result ?: emit(DataState.NetworkError())
        result?.let {
            when (result.code()) {
                200 -> {
                    result.body() ?: emit(DataState.SomeError())
                    result.body()?.let {
                        emit(DataState.Success(restaurantDetailsMapper.mapToDomainModel(it)))
                    }
                }
                else -> {
                    DataState.SomeError()
                }
            }
        }
    }
}