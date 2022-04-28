package com.digimoplus.foodninja.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.digimoplus.foodninja.domain.util.ExperimentalOnlineChecker
import com.digimoplus.foodninja.domain.util.OnlineChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext app: Context): DataStore<Preferences> = app.dataStore

    @Singleton
    @Provides
    fun provideOnlineChecker(): OnlineChecker {
        val runtime = Runtime.getRuntime()
        return ExperimentalOnlineChecker(runtime)
    }




}