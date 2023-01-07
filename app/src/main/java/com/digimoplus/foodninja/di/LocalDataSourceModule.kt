package com.digimoplus.foodninja.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.data.db.BasketDao
import com.digimoplus.foodninja.data.db.ProductDao
import com.digimoplus.foodninja.domain.repository.data_source.BasketLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source_impl.BasketLocalDataSourceImpl
import com.digimoplus.foodninja.domain.repository.data_source_impl.DataStoreLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideBasketLocalDataSource(
        productDao: ProductDao,
        basketDao: BasketDao,
    ): BasketLocalDataSource = BasketLocalDataSourceImpl(
        productDao = productDao,
        basketDao = basketDao,
    )

    @Singleton
    @Provides
    fun provideDataStoreLocalDataSource(
        dataStore: DataStore<Preferences>,
    ): DataStoreLocalDataSource = DataStoreLocalDataSourceImpl(
        dataStore = dataStore
    )
}