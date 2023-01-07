package com.digimoplus.foodninja.domain.repository.data_source

import com.digimoplus.foodninja.data.api.model.MenuDetailDto
import com.digimoplus.foodninja.data.api.model.MenuDto
import com.digimoplus.foodninja.data.api.model.MenuListDto
import retrofit2.Response

interface MenuRemoteDataSource {

    suspend fun getPopularMenus(token: String): Response<List<MenuDto>>?

    suspend fun getMenusList(token: String, page: Int): Response<MenuListDto>?

    suspend fun menuSearch(token: String, search: String, page: Int): Response<MenuListDto>?

    suspend fun getMenuDetails(token: String, menuId: Int): Response<MenuDetailDto>?

}