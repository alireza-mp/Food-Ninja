package com.digimoplus.foodninja.data.api

import com.digimoplus.foodninja.data.api.model.RegisterDto
import com.digimoplus.foodninja.data.api.model.ResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RegisterService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,
    ): Response<RegisterDto>


    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/add_info")
    suspend fun completeUserRegister(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("family") family: String,
        @Field("phone") phone: String,
        @Field("location") location: String,
    ): Response<ResponseDto>

    @Headers("Accept: application/json")
    @Multipart
    @POST("auth/upload")
    suspend fun uploadUserProfile(
        @Part part: MultipartBody.Part,
        @Part("id") id: RequestBody,
    ): Response<ResponseDto>

}