package com.digimoplus.foodninja.network

import com.digimoplus.foodninja.network.model.*
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
        @Field("location") location: String,
    ): Response<MessageDto>

    @Headers("Accept: application/json")
    @Multipart
    @POST("auth/upload")
    suspend fun uploadUserProfile(
        @Part part: MultipartBody.Part,
        @Part("id") id: RequestBody,
    ): Response<MessageDto>


    @Headers("Accept: application/json")
    @GET("menu")
    suspend fun menuList(@Header("Authorization") token: String): Response<List<MenuDto>>

    @Headers("Accept: application/json")
    @GET("restaurant")
    suspend fun restaurantList(@Header("Authorization") token: String): Response<List<RestaurantDto>>

    @Headers("Accept: application/json")
    @GET("restaurant_all")
    suspend fun allRestaurantsList(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
    ): Response<RestaurantList>

    @Headers("Accept: application/json")
    @GET("restaurant_search")
    suspend fun restaurantSearch(
        @Header("Authorization") token: String,
        @Query("search") search: String,
        @Query("page") page: Int,
    ): Response<RestaurantList>

    @Headers("Accept: application/json")
    @GET("menu_search")
    suspend fun menuSearch(
        @Header("Authorization") token: String,
        @Query("search") search: String,
        @Query("page") page: Int,
    ): Response<MenuList>

    @Headers("Accept: application/json")
    @GET("menu_all")
    suspend fun allMenuList(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
    ): Response<MenuList>

    @Headers("Accept: application/json")
    @GET("restaurant_detail")
    suspend fun restaurantDetails(
        @Header("Authorization") token: String,
        @Query("restaurant_id") id: Int,
    ): Response<RestoDetailDto>

}