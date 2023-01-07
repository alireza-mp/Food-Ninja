package com.digimoplus.foodninja.domain.repository.data_source_impl

import com.digimoplus.foodninja.data.db.BasketDao
import com.digimoplus.foodninja.data.db.ProductDao
import com.digimoplus.foodninja.data.db.model.BasketTable
import com.digimoplus.foodninja.domain.repository.data_source.BasketLocalDataSource
import javax.inject.Inject

class BasketLocalDataSourceImpl
@Inject
constructor(
    private val productDao: ProductDao,
    private val basketDao: BasketDao,
) : BasketLocalDataSource {

    override suspend fun basketList(userId: Int): List<BasketTable>? {
        return try {
            basketDao.basketList(userId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteBasketItem(id: Int) {
        return try {
            basketDao.deleteBasketItem(id)
        } catch (_: Exception) {

        }
    }

    override suspend fun updateCount(id: Int, count: Int) {
        return try {
            basketDao.updateCount(id, count)
        } catch (_: Exception) {

        }
    }

    override suspend fun addNewItemToBasket(basketTable: BasketTable): Long? {
        return try {
            productDao.addToBasket(basketTable)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getItemCount(userId: Int, menuId: Int): BasketTable? {
        return try {
            val data = productDao.getItemCount(userId, menuId)
            if (data.isEmpty()) null else data[0]
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun isCurrentRestaurant(restaurantId: Int): Boolean? {
        return try {
            productDao.isCurrentRestaurant(restaurantId).isNotEmpty()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteCurrentRestaurant(restaurantId: Int) {
        try {
            productDao.deleteCurrentRestaurant(restaurantId)
        } catch (_: Exception) {

        }
    }

    override suspend fun getItemsCount(userId: Int): Int {
        return basketDao.basketList(userId).size
    }


}
