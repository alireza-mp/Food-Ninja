package com.digimoplus.foodninja.domain.useCase.home_detail

import com.digimoplus.foodninja.domain.model.HomeDetail
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.useCase.menus.GetPopularMenusListUseCase
import com.digimoplus.foodninja.domain.useCase.restaurants.GetNearestRestaurantsListUseCase
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeDetailUseCase
@Inject
constructor(
    private val getPopularMenusListUseCase: GetPopularMenusListUseCase,
    private val getNearestRestaurantsListUseCase: GetNearestRestaurantsListUseCase,
) {

    suspend operator fun invoke() = initialData()

    private suspend fun initialData(): Flow<DataState<HomeDetail>> = callbackFlow {

        var popularMenus: List<Menu>? = null
        var nearestRestaurants: List<Restaurant>? = null

        send(DataState.Loading)

        // get nearest restaurants
        launch {
            getNearestRestaurantsListUseCase().collect {
                when (it) {
                    is DataState.Loading -> {

                    }
                    is DataState.Success -> {
                        nearestRestaurants = it.data
                        popularMenus?.let { menus ->
                            send(DataState.Success(HomeDetail(
                                menuList = menus,
                                restaurantList = it.data,
                            )))
                        }
                    }
                    else -> {
                        trySend(DataState.NetworkError())
                        channel.close()
                    }
                }
            }
        }
        // get menus
        launch {
            getPopularMenusListUseCase().collect {
                when (it) {
                    is DataState.Loading -> {}
                    is DataState.Success -> {
                        popularMenus = it.data
                        nearestRestaurants?.let { restaurants ->
                            send(DataState.Success(HomeDetail(
                                restaurantList = restaurants,
                                menuList = it.data,
                            )))
                        }
                    }
                    else -> {
                        trySend(DataState.NetworkError())
                        channel.close()
                    }
                }
            }
        }

        awaitClose {
            channel.close()
        }
    }

}