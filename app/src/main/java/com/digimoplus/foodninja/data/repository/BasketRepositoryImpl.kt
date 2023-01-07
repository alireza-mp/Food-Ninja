package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.data.db.model.BasketTableMapper
import com.digimoplus.foodninja.data.db.model.BasketTableMenuMapper
import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.domain.model.MenuDetailInfo
import com.digimoplus.foodninja.domain.repository.BasketRepository
import com.digimoplus.foodninja.domain.repository.data_source.BasketLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BasketRepositoryImpl
@Inject
constructor(
    private val basketLocalDataSource: BasketLocalDataSource,
    private val mapper: BasketTableMapper,
    private val basketTableMenuMapper: BasketTableMenuMapper,
    private val dataStore: DataStoreLocalDataSource,
) : BasketRepository {

    override suspend fun basketList(): Flow<DataState<List<Basket>>> = flow {
        emit(DataState.Loading)
        val result = basketLocalDataSource.basketList(dataStore.getUserId())
        result ?: emit(DataState.SomeError())
        result?.let {
            emit(DataState.Success(mapper.mapToDomainList(result)))
        }
    }

    override suspend fun deleteBasketItem(id: Int) {
        basketLocalDataSource.deleteBasketItem(id)
    }

    override suspend fun updateCount(id: Int, count: Int) {
        basketLocalDataSource.updateCount(id, count)
    }

    override suspend fun addNewItemToBasket(
        menuDetailInfo: MenuDetailInfo,
    ): Flow<DataState<Long>> = flow {
        emit(DataState.Loading)
        val result = basketLocalDataSource.addNewItemToBasket(
            basketTableMenuMapper.mapFromDomainModel(
                id = 0,
                model = menuDetailInfo,
                userId = dataStore.getUserId(),
                count = 1 // default one
            )
        )
        result ?: emit(DataState.SomeError())
        result?.let {
            emit(DataState.Success(result))
        }
    }

    override suspend fun getBasketItem(menuId: Int): Flow<DataState<Basket?>> = flow {
        emit(DataState.Loading)
        val result = basketLocalDataSource.getItemCount(dataStore.getUserId(), menuId)
        result ?: emit(DataState.Success(null))
        result?.let {
            emit(DataState.Success(mapper.mapToDomainModel(result)))
        }
    }

    override suspend fun isCurrentRestaurant(restaurantId: Int): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        val result = basketLocalDataSource.isCurrentRestaurant(restaurantId)
        result ?: emit(DataState.SomeError())
        result?.let {
            emit(DataState.Success(result))
        }
    }

    override suspend fun deleteCurrentRestaurant(restaurantId: Int) {
        basketLocalDataSource.deleteCurrentRestaurant(restaurantId)
    }

    override suspend fun getBasketItemsCount(): Int =
        basketLocalDataSource.getItemsCount(dataStore.getUserId())


}