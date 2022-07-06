package com.digimoplus.foodninja.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.network.model.MenuDetailDtoMapper
import com.digimoplus.foodninja.network.model.MenuDtoMapper
import com.digimoplus.foodninja.network.model.RestaurantDtoMapper
import com.digimoplus.foodninja.network.model.RestoDetailDtoMapper
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
    fun provideRegisterUserRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>,
    ): RegisterUserRepository {
        return RegisterUserRepositoryImpl(
            authService = authService, dataStore = dataStore
        )
    }

    @Singleton
    @Provides
    fun provideUploadPhotoRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>,
    ): UploadPhotoRepository {
        return UploadPhotoRepositoryImpl(
            authService = authService, dataStore = dataStore
        )
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        authService: AuthService,
        dataStore: DataStore<Preferences>,
        restaurantMapper: RestaurantDtoMapper,
        menuMapper: MenuDtoMapper,
    ): HomeRepository {
        return HomeRepositoryImpl(
            authService = authService,
            dataStore = dataStore,
            restaurantMapper = restaurantMapper,
            menuMapper = menuMapper
        )
    }
    @Singleton
    @Provides
    fun provideRestoDetailRepository(
        authService: AuthService,
        restoDetailDtoMapper: RestoDetailDtoMapper,
    ): RestoDetailRepository {
        return RestoDetailRepositoryImpl(
            authService = authService,
            mapper = restoDetailDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideMenuDetailRepository(
        authService: AuthService,
        menuDetailDtoMapper: MenuDetailDtoMapper,
    ): MenuDetailRepository {
        return MenuDetailRepositoryImpl(
            authService = authService,
            mapper = menuDetailDtoMapper,
        )
    }

}