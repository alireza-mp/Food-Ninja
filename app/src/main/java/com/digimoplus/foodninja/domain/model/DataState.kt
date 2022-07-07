package com.digimoplus.foodninja.domain.model



sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class WrongLoginError(val message: String = "Wrong  Email or Password!") : DataState<Nothing>()
    data class SomeError(val message: String = "Some times Error !") : DataState<Nothing>()
    data class InvalidLoginError(val message: String = "Invalid Email or Password!") : DataState<Nothing>()
    data class NetworkError(val message: String = "No Internet Connection!") : DataState<Nothing>()
    data class SuccessMessage(val message: String = "Success") : DataState<Nothing>()

}


