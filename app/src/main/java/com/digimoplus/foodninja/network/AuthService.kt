package com.digimoplus.foodninja.network

import com.digimoplus.foodninja.network.model.AddInfoDto
import com.digimoplus.foodninja.network.model.RegisterDto
import com.digimoplus.foodninja.network.model.UserDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface AuthService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,
    ): Response<RegisterDto>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<RegisterDto>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/add_info")
    suspend fun addUserInfo(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("family") family: String,
        @Field("phone") phone: String,
    ): Response<AddInfoDto>
}