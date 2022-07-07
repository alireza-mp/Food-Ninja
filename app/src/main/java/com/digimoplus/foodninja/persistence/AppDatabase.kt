package com.digimoplus.foodninja.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.digimoplus.foodninja.persistence.model.BasketTable

@Database(entities = [BasketTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "foodninja_db"
    }

}