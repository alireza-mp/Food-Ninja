package com.digimoplus.foodninja.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.digimoplus.foodninja.data.db.model.BasketTable

@Database(entities = [BasketTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getBasketDao(): BasketDao

    companion object {
        const val DATABASE_NAME = "foodninja_db"
    }

}