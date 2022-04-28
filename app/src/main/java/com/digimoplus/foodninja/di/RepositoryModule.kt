package com.digimoplus.foodninja.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.repository.SignInRepository
import com.digimoplus.foodninja.repository.SignInRepositoryImpl
import com.digimoplus.foodninja.repository.SignUpRepository
import com.digimoplus.foodninja.repository.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSignUpRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>,
    ):SignUpRepository{
        return SignUpRepositoryImpl(
            authService = authService,
            dataStore = dataStore,
        )
    }

    @Singleton
    @Provides
    fun provideSignInRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>,
    ): SignInRepository {
        return SignInRepositoryImpl(
            authService = authService,
            dataStore = dataStore,
        )
    }
}