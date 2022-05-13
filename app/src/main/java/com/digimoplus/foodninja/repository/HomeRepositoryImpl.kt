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


class HomeRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
    private val restaurantMapper: RestaurantDtoMapper,
    private val menuMapper: MenuDtoMapper,
) : HomeRepository {

    override suspend fun getRestaurantList(token: String): DataState<List<Restaurant>> {
        return try {
            val results = authService.restaurantList(token)
            when (results.code()) {
                404 -> {
                    DataState.InvalidError()
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

    override suspend fun getMenuList(token: String): DataState<List<Menu>> {
        return try {
            val results = authService.menuList(token)
            when (results.code()) {
                404 -> {
                    DataState.InvalidError()
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