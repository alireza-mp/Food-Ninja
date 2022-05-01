package com.digimoplus.foodninja.network

import com.digimoplus.foodninja.network.model.MessageDto
import com.digimoplus.foodninja.network.model.RegisterDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    ): Response<MessageDto>

    @Headers("Accept: application/json")
    @Multipart
    @POST("auth/upload")
    suspend fun uploadUserProfile(
        @Part part: MultipartBody.Part,
        @Part("id") id: RequestBody
    ): Response<MessageDto>
}