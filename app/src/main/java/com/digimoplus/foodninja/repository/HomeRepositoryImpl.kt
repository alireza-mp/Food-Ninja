package com.digimoplus.foodninja.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.network.model.MenuDtoMapper
import com.digimoplus.foodninja.network.model.RestaurantDtoMapper
import javax.inject.Inject

const val PAGE_SIZE = 16

class HomeRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
    private val restaurantMapper: RestaurantDtoMapper,
    private val menuMapper: MenuDtoMapper,
) : HomeRepository {

    var lastPage = -1

    override suspend fun getRestaurantList(token: String): DataState<List<Restaurant>> {
        return try {
            val results = authService.restaurantList(token)
            when (results.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    DataState.Success(
                        restaurantMapper.mapToDomainList(results.body() ?: listOf())
                    )
                }
                else -> {
                    DataState.SomeError()
                }
            }
        } catch (e: Exception) {
            DataState.NetworkError()
        }

    }

    override suspend fun restaurantSearch(
        token: String,
        search: String,
        page: Int,
    ): DataState<List<Restaurant>> {
        return try {
            val results = authService.restaurantSearch(token, search, page)
            when (results.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    DataState.Success(
                        restaurantMapper.mapToDomainList(results.body()?.data ?: listOf())
                    )
                }
                else -> {
                    DataState.SomeError()
                }
            }
        } catch (e: Exception) {
            DataState.NetworkError()
        }
    }

    override suspend fun getAllRestaurantsList(
        token: String,
        page: Int,
    ): DataState<List<Restaurant>> {
        return try {
            val results = authService.allRestaurantsList(token, page)
            when (results.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    lastPage = results.body()?.lastPage ?: -1
                    DataState.Success(
                        restaurantMapper.mapToDomainList(results.body()?.data ?: listOf())
                    )
                }
                else -> {
                    DataState.SomeError()
                }
            }
        } catch (e: Exception) {
            DataState.NetworkError()
        }
    }

    override suspend fun getMenuList(token: String): DataState<List<Menu>> {
        return try {
            val results = authService.menuList(token)
            when (results.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    DataState.Success(
                        menuMapper.mapToDomainList(results.body() ?: listOf())
                    )
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