package com.digimoplus.foodninja.domain.model

sealed class RegisterState<out R>(val message: String) {
    data class Successful<out T>(val data: T) : RegisterState<T>("Successful Registered")
    object Loading : RegisterState<Nothing>("loading")
    object NetworkError : RegisterState<Nothing>("No Internet Connection!")
    object WrongError : RegisterState<Nothing>("Wrong  Email or Password!")
    object SomeError : RegisterState<Nothing>("Some times Error !")
    object InvalidError : RegisterState<Nothing>("Invalid Email or Password!")
    object InvalidNameError : RegisterState<Nothing>("Name not entered!")
}
