package com.digimoplus.foodninja.domain.model

enum class Register(val message:String,){
     NetworkError("No Internet Connection!"),
     InvalidError ("Invalid Email or Password!"),
     Successful ("Successful Registered"),
     SomeError ("Some times Error !"),

}
