package com.digimoplus.foodninja.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.repository.HomeRepository
import com.digimoplus.foodninja.data.api.AuthService
import com.digimoplus.foodninja.data.api.model.MenuDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDtoMapper
import javax.inject.Inject

// number of items in one request
const val PAGE_SIZE = 16

// home repository used in : mainContent & restaurant content & menu content & search content
class HomeRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
    private val restaurantMapper: RestaurantDtoMapper,
    private val menuMapper: MenuDtoMapper,
) : HomeRepository {

    // number of pages
    var lastPage = -1

    //get restaurants list : main content
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

    //get menu list : main content
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

    // search in restaurants  : restaurant content
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

    //get all restaurants list with page : restaurant content
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

    // search in menus  : menu content
    override suspend fun menuSearch(
        token: String,
        search: String,
        page: Int,
    ): DataState<List<Menu>> {
        return try {
            val results = authService.menuSearch(token, search, page)
            when (results.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    lastPage = results.body()?.lastPage ?: -1
                    DataState.Success(
                        menuMapper.mapToDomainList(results.body()?.data ?: listOf())
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

    //get all menu list with page : menu content
    override suspend fun getAllMenuList(token: String, page: Int): DataState<List<Menu>> {
        return try {
            val results = authService.allMenuList(token, page)
            when (results.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    lastPage = results.body()?.lastPage ?: -1
                    DataState.Success(
                        menuMapper.mapToDomainList(results.body()?.data ?: listOf())
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