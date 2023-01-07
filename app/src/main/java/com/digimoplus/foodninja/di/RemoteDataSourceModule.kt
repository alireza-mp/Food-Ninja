package com.digimoplus.foodninja.di

import com.digimoplus.foodninja.data.api.LoginService
import com.digimoplus.foodninja.data.api.MenuService
import com.digimoplus.foodninja.data.api.RegisterService
import com.digimoplus.foodninja.data.api.RestaurantService
import com.digimoplus.foodninja.domain.repository.data_source.*
import com.digimoplus.foodninja.domain.repository.data_source_impl.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.Socket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {


    @Singleton
    @Provides
    fun provideMenusRemoteDataSource(
        menuService: MenuService,
    ): MenuRemoteDataSource = MenuRemoteDataSourceImpl(
        menuService = menuService
    )

    @Singleton
    @Provides
    fun provideRestaurantsRemoteDataSource(
        restaurantService: RestaurantService,
    ): RestaurantRemoteDataSource = RestaurantRemoteDataSourceImpl(
        restaurantService = restaurantService
    )

    @Singleton
    @Provides
    fun provideRegisterRemoteDataSource(
        registerService: RegisterService,
    ): RegisterRemoteDataSource = RegisterRemoteDataSourceImpl(
        registerService = registerService,
    )

    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(
        loginService: LoginService,
    ): LoginRemoteDataSource = LoginRemoteDataSourceImpl(
        loginService = loginService,
    )

    @Singleton
    @Provides
    fun provideChatRemoteDataSource(
        socket: Socket,
        gson: Gson,
    ): ChatRemoteDataSource = ChatRemoteDataSourceImpl(
        socket = socket,
        gson = gson,
    )

}