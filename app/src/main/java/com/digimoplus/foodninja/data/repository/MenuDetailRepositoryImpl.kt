package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.domain.model.MenuDetailInfo
import com.digimoplus.foodninja.domain.repository.MenuDetailRepository
import com.digimoplus.foodninja.data.api.AuthService
import com.digimoplus.foodninja.data.api.model.MenuDetailDtoMapper
import com.digimoplus.foodninja.data.db.ProductDao
import com.digimoplus.foodninja.data.db.model.BasketTableMenuMapper
import javax.inject.Inject


class MenuDetailRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val menuDetailDtoMapper: MenuDetailDtoMapper,
    private val productDao: ProductDao,
    private val basketTableMenuMapper: BasketTableMenuMapper,

    ) : MenuDetailRepository {

    var basketId = 0L

    override suspend fun getMenuDetails(token: String, menuId: Int): DataState<MenuDetail> {
        return try {
            val result = authService.menuDetails(token = token, id = menuId)
            when (result.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    val data = result.body()
                    if (data != null) {
                        DataState.Success(
                            menuDetailDtoMapper.mapToDomainModel(model = data)
                        )
                    } else {
                        DataState.SomeError()
                    }
                }
                else -> {
                    DataState.SomeError()
                }
            }
        } catch (e: Exception) {
            DataState.NetworkError()
        }
    }

    override suspend fun addToBasket(
        menuDetailInfo: MenuDetailInfo,
        userId: Int,
        count: Int,
    ): DataState<Nothing> {
        return try {
            val result = basketTableMenuMapper.mapFromDomainModel(
                model = menuDetailInfo,
                tableId = basketId.toInt(),
                userId = userId,
                count = count
            )
            basketId = productDao.addToBasket(result)
            DataState.SuccessMessage()
        } catch (e: Exception) {
            DataState.SomeError()
        }
    }

    override suspend fun checkIsExist(
        userId: Int,
        menuId: Int,
    ): Int {
        val results = productDao.checkIsExist(userId = userId, menuId = menuId)
        return if (results.isNotEmpty()) {
            basketId = results[0].id.toLong()
            results[0].count
        } else {
            basketId = 0
            0
        }
    }

    override suspend fun deleteBasketItem(userId: Int, menuId: Int) {
        basketId = 0
        productDao.deleteBasketItem(userId = userId, menuId = menuId)
    }

    override suspend fun checkRestaurants(restaurantId: Int): Boolean =
        productDao.checkOtherRestaurants(restaurantId).isNotEmpty()


    override suspend fun removeOtherRestaurants(restaurantId: Int) {
        productDao.deleteOtherRestaurants(restaurantId)
    }


}