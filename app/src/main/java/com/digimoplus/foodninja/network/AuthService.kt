package com.digimoplus.foodninja.network

import com.digimoplus.foodninja.network.model.RegisterDto
import com.digimoplus.foodninja.network.model.UserDto
import retrofit2.Response
import retrofit2.http.*

interface AuthService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun SignUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,
    ): Response<RegisterDto>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun SignIn(
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserDto
}