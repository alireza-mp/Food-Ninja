package com.digimoplus.foodninja.data.api

import com.digimoplus.foodninja.data.api.model.MenuDetailDto
import com.digimoplus.foodninja.data.api.model.MenuDto
import com.digimoplus.foodninja.data.api.model.MenuListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MenuService {

    @GET("menu_detail")
    suspend fun menuDetails(
        @Header("Authorization") token: String,
        @Query("menu_id") id: Int,
    ): Response<MenuDetailDto>


    @GET("menu_all")
    suspend fun menusList(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
    ): Response<MenuListDto>

    @GET("menu_search")
    suspend fun menuSearch(
        @Header("Authorization") token: String,
        @Query("search") search: String,
        @Query("page") page: Int,
    ): Response<MenuListDto>

    @GET("menu")
    suspend fun popularMenus(@Header("Authorization") token: String): Response<List<MenuDto>>
}