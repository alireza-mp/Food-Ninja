package com.digimoplus.foodninja.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.persistence.ProductDao
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MainRepositoryImpl
@Inject
constructor(
    private val productDao: ProductDao,
    private val dataStore: DataStore<Preferences>,
) : MainRepository {

    override suspend fun checkBasketItemsCount(): Int {
        val results = productDao.checkBasketItemsCount(getUserId())
        return results.size
    }

    private suspend fun getUserId(): Int {
        return dataStore.data.first()[PreferencesKeys.userId] ?: 0
    }

}