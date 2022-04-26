package com.digimoplus.foodninja.network.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any?,
    @SerializedName("family")
    val family: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("updated_at")
    val updatedAt: String
)