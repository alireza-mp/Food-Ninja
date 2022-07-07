package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.domain.model.MenuDetailInfo

interface MenuDetailRepository {

    suspend fun getMenuDetails(token: String, menuId: Int): DataState<MenuDetail>
    suspend fun addToBasket(
        menuDetailInfo: MenuDetailInfo,
        userId: Int,
        count: Int,
    ): DataState<Nothing>

    // check if menu exist into basket and get count
    suspend fun checkIsExist(
        userId: Int,
        menuId: Int,
    ): Int

    //
    suspend fun deleteBasketItem(
        userId: Int,
        menuId: Int,
    )

}