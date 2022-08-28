package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.RestoDetail

interface RestoDetailRepository {

    // get page details from server
    suspend fun getRestaurantDetails(token: String, restaurantId: Int): DataState<RestoDetail>


}