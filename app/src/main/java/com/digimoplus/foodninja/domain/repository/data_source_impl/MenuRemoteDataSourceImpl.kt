package com.digimoplus.foodninja.domain.repository.data_source_impl

import com.digimoplus.foodninja.data.api.MenuService
import com.digimoplus.foodninja.data.api.model.MenuDetailDto
import com.digimoplus.foodninja.data.api.model.MenuDto
import com.digimoplus.foodninja.data.api.model.MenuListDto
import com.digimoplus.foodninja.domain.repository.data_source.MenuRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class MenuRemoteDataSourceImpl
@Inject
constructor(
    private val menuService: MenuService,
) : MenuRemoteDataSource {
    override suspend fun getPopularMenus(token: String): Response<List<MenuDto>>? {
        return try {
            menuService.popularMenus(token)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMenusList(token: String, page: Int): Response<MenuListDto>? {
        return try {
            menuService.menusList(token, page)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun menuSearch(
        token: String,
        search: String,
        page: Int,
    ): Response<MenuListDto>? {
        return try {
            menuService.menuSearch(token, search, page)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMenuDetails(token: String, menuId: Int): Response<MenuDetailDto>? {
        return try {
            menuService.menuDetails(token, menuId)
        } catch (e: Exception) {
            null
        }
    }
}