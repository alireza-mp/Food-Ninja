package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant

interface HomeRepository {


    suspend fun getRestaurantList(token: String): DataState<List<Restaurant>>

    suspend fun getMenuList(token: String): DataState<List<Menu>>

}