package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.repository.BasketRepository
import com.digimoplus.foodninja.data.db.BasketDao
import com.digimoplus.foodninja.data.db.model.BasketTableMapper
import javax.inject.Inject

class BasketRepositoryImpl
@Inject
constructor(
    private val basketDao: BasketDao,
    private val mapper: BasketTableMapper,
) : BasketRepository {

    override suspend fun basketList(): DataState<List<Basket>> {
        return try {
            val result = mapper.mapToDomainList(basketDao.basketList())
            DataState.Success(result)

        } catch (e: Exception) {
            DataState.SomeError()
        }
    }

    override suspend fun deleteBasketItem(id: Int) {
        basketDao.deleteBasketItem(id)
    }

    override suspend fun updateCount(id: Int, count: Int) {
        basketDao.updateCountBasketItem(id, count)
    }

}