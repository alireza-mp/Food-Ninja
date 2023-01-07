package com.digimoplus.foodninja.data.api

import com.digimoplus.foodninja.data.api.model.ResponseDto
import retrofit2.Response
import retrofit2.http.*

interface BasketService {


    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("add_to_basket")
    suspend fun addToBasket(
        @Header("Authorization") token: String,
        @Field("user_id") userId: Int,
        @Field("menu_id") menuId: Int,
        @Field("count") count: Int,
    ): Response<ResponseDto>

}