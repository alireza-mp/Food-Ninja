package com.digimoplus.foodninja.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.data.repository.*
import com.digimoplus.foodninja.domain.repository.*
import com.digimoplus.foodninja.data.api.AuthService
import com.digimoplus.foodninja.data.api.model.MenuDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.MenuDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDtoMapper
import com.digimoplus.foodninja.data.api.model.RestoDetailDtoMapper
import com.digimoplus.foodninja.data.api.soketio.MessageDtoMapper
import com.digimoplus.foodninja.data.db.BasketDao
import com.digimoplus.foodninja.data.db.ProductDao
import com.digimoplus.foodninja.data.db.model.BasketTableMapper
import com.digimoplus.foodninja.data.db.model.BasketTableMenuMapper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.Socket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        productDao: ProductDao,
        dataStore: DataStore<Preferences>,
    ): MainRepository {
        return MainRepositoryImpl(
            productDao = productDao,
            dataStore = dataStore,
        )
    }

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
        basketTableMenuMapper: BasketTableMenuMapper,
        productDao: ProductDao,
    ): MenuDetailRepository {
        return MenuDetailRepositoryImpl(
            authService = authService,
            menuDetailDtoMapper = menuDetailDtoMapper,
            productDao = productDao,
            basketTableMenuMapper = basketTableMenuMapper
        )
    }

    @Singleton
    @Provides
    fun provideBasketRepository(
        basketDao: BasketDao,
        mapper: BasketTableMapper,
    ): BasketRepository {
        return BasketRepositoryImpl(
            basketDao = basketDao,
            mapper = mapper
        )
    }

    @Singleton
    @Provides
    fun provideChatDetailRepository(
        socket: Socket,
        gson: Gson,
        mapper: MessageDtoMapper,
    ): ChatDetailRepository {
        return ChatDetailRepositoryImpl(
            socket = socket,
            gson = gson,
            mapper = mapper
        )
    }

}