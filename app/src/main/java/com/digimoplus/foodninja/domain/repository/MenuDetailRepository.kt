package com.digimoplus.foodninja.domain.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.domain.model.MenuDetailInfo

interface MenuDetailRepository {

    // get page details from server
    suspend fun getMenuDetails(token: String, menuId: Int): DataState<MenuDetail>

    // add menu item in basket
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

    //delete menu item in basket
    suspend fun deleteBasketItem(
        userId: Int,
        menuId: Int,
    )

    //check is other restaurants in basket exist
    suspend fun checkRestaurants(
        restaurantId: Int
    ): Boolean

    //remove other restaurants from basket
    suspend fun removeOtherRestaurants(
        restaurantId :Int
    )

}