package com.digimoplus.foodninja.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.repository.*
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
    ): SignUpRepository {
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

    @Singleton
    @Provides
    fun provideUserInfoRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>
    ): UserInfoRepository {
        return UserInfoRepositoryImpl(
            authService = authService, dataStore = dataStore
        )
    }

    @Singleton
    @Provides
    fun provideUploadPhotoRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>
    ): UploadPhotoRepository {
        return UploadPhotoRepositoryImpl(
            authService = authService, dataStore = dataStore
        )
    }
}