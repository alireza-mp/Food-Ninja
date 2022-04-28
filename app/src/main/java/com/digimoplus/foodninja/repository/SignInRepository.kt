package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Register

interface SignInRepository {

suspend fun loginUser (email:String,password:String) : Register

}