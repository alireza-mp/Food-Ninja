package com.digimoplus.foodninja.di

import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.network.soketio.MessageDtoMapper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SocketIOModule {

    @Singleton
    @Provides
    fun provideSocket(): Socket {
        return IO.socket(Constants.BASE_SOCKET_URL)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideMessageDtoMapper(): MessageDtoMapper {
        return MessageDtoMapper()
    }


}