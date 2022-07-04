package com.digimoplus.foodninja.di

import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.network.model.MenuDtoMapper
import com.digimoplus.foodninja.network.model.RestaurantDtoMapper
import com.digimoplus.foodninja.network.model.RestoDetailDtoMapper
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
    fun provideAuthService(builder: Retrofit.Builder): AuthService {
        return builder.build().create(AuthService::class.java)
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
    fun provideRestoDetailDtoMapper(): RestoDetailDtoMapper {
        return RestoDetailDtoMapper()
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