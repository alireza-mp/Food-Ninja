package com.digimoplus.foodninja.domain.repository


import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.domain.model.MenuList
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun getPopularMenuList(): Flow<DataState<List<Menu>>>

    suspend fun getMenusList(page: Int): Flow<DataState<MenuList>>

    suspend fun menuSearch(
        search: String,
        page: Int,
    ): Flow<DataState<MenuList>>

    suspend fun getMenuDetails(menuId: Int): Flow<DataState<MenuDetail>>

}