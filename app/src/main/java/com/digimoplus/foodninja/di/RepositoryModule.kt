package com.digimoplus.foodninja.di

import com.digimoplus.foodninja.data.api.model.MenuDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.MenuDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDtoMapper
import com.digimoplus.foodninja.data.api.soketio.MessageDtoMapper
import com.digimoplus.foodninja.data.db.model.BasketTableMapper
import com.digimoplus.foodninja.data.db.model.BasketTableMenuMapper
import com.digimoplus.foodninja.data.repository.*
import com.digimoplus.foodninja.domain.repository.*
import com.digimoplus.foodninja.domain.repository.data_source.*
import com.google.gson.Gson
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
    fun provideRegisterRepository(
        registerRemoteDataSource: RegisterRemoteDataSource,
        dataStore: DataStoreLocalDataSource,
    ): RegisterRepository {
        return RegisterRepositoryImpl(
            registerRemoteDataSource = registerRemoteDataSource,
            dataStore = dataStore
        )
    }

    @Singleton
    @Provides
    fun provideLoginRepositoryImpl(
        loginRemoteDataSource: LoginRemoteDataSource,
        dataStore: DataStoreLocalDataSource,
    ): LoginRepository {
        return LoginRepositoryImpl(
            loginRemoteDataSource = loginRemoteDataSource,
            dataStore = dataStore,
        )
    }

    @Singleton
    @Provides
    fun provideRestaurantRepository(
        restaurantRemoteDataSource: RestaurantRemoteDataSource,
        dataStoreLocalDataSource: DataStoreLocalDataSource,
        restaurantDetailsMapper: RestaurantDetailDtoMapper,
        restaurantMapper: RestaurantDtoMapper,
    ): RestaurantRepository =
        RestaurantRepositoryImpl(
            restaurantRemoteDataSource = restaurantRemoteDataSource,
            dataStore = dataStoreLocalDataSource,
            restaurantMapper = restaurantMapper,
            restaurantDetailsMapper = restaurantDetailsMapper,
        )

    @Singleton
    @Provides
    fun provideMenuRepository(
        menuRemoteDataSource: MenuRemoteDataSource,
        dataStoreLocalDataSource: DataStoreLocalDataSource,
        menuDetailDtoMapper: MenuDetailDtoMapper,
        menuMapper: MenuDtoMapper,
    ): MenuRepository =
        MenuRepositoryImpl(
            menuRemoteDataSource = menuRemoteDataSource,
            dataStore = dataStoreLocalDataSource,
            menuMapper = menuMapper,
            menuDetailDtoMapper = menuDetailDtoMapper,
        )

    @Singleton
    @Provides
    fun provideBasketRepository(
        dataStoreLocalDataSource: DataStoreLocalDataSource,
        basketTableMenuMapper: BasketTableMenuMapper,
        mapper: BasketTableMapper,
        basketLocalDataSource: BasketLocalDataSource,
    ): BasketRepository {
        return BasketRepositoryImpl(
            basketLocalDataSource = basketLocalDataSource,
            mapper = mapper,
            basketTableMenuMapper = basketTableMenuMapper,
            dataStore = dataStoreLocalDataSource,
        )
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        dataStore: DataStoreLocalDataSource,
    ): ProfileRepository {
        return ProfileRepositoryImpl(
            dataStoreLocalDataSource = dataStore,
        )
    }

    @Singleton
    @Provides
    fun provideChatDetailRepository(
        dataStore: DataStoreLocalDataSource,
        chatRemoteDataSource: ChatRemoteDataSource,
        mapper: MessageDtoMapper,
        gson: Gson,
    ): ChatRepository {
        return ChatRepositoryImpl(
            dataStore = dataStore,
            chatRemoteDataSource = chatRemoteDataSource,
            mapper = mapper,
            gson = gson,
        )
    }

}