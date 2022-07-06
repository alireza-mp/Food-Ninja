package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.MenuDetail

interface MenuDetailRepository {

    suspend fun getMenuDetails(token: String, menuId: Int): DataState<MenuDetail>

}