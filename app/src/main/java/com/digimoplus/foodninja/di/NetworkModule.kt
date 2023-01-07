package com.digimoplus.foodninja.di

import com.digimoplus.foodninja.data.api.*
import com.digimoplus.foodninja.data.api.model.MenuDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.MenuDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.RestaurantDtoMapper
import com.digimoplus.foodninja.domain.util.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    @Singleton
    @Provides
    fun provideLoginService(builder: Retrofit.Builder): LoginService {
        return builder.build().create(LoginService::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterService(builder: Retrofit.Builder): RegisterService {
        return builder.build().create(RegisterService::class.java)
    }

    @Singleton
    @Provides
    fun provideMenuService(builder: Retrofit.Builder): MenuService {
        return builder.build().create(MenuService::class.java)
    }

    @Singleton
    @Provides
    fun provideRestaurantService(builder: Retrofit.Builder): RestaurantService {
        return builder.build().create(RestaurantService::class.java)
    }

    @Singleton
    @Provides
    fun provideBasketService(builder: Retrofit.Builder): BasketService {
        return builder.build().create(BasketService::class.java)
    }

    @Singleton
    @Provides
    fun provideRestaurantDtoMapper(): RestaurantDtoMapper {
        return RestaurantDtoMapper()
    }

    @Singleton
    @Provides
    fun provideMenuDtoMapper(): MenuDtoMapper {
        return MenuDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRestoDetailDtoMapper(): RestaurantDetailDtoMapper {
        return RestaurantDetailDtoMapper()
    }

    @Singleton
    @Provides
    fun provideMenuDetailDtoMapper(): MenuDetailDtoMapper {
        return MenuDetailDtoMapper()
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


}