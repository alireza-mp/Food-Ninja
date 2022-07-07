package com.digimoplus.foodninja.di

import android.app.Application
import androidx.room.Room
import com.digimoplus.foodninja.persistence.AppDatabase
import com.digimoplus.foodninja.persistence.ProductDao
import com.digimoplus.foodninja.persistence.model.BasketTableMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDB(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao {
        return db.getProductDao()
    }

    @Singleton
    @Provides
    fun provideBasketTableMapper(): BasketTableMapper {
        return BasketTableMapper()
    }


}