package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.RestoDetail

interface RestoDetailRepository {

    suspend fun getDetails(token: String, restaurantId: Int): DataState<RestoDetail>


}