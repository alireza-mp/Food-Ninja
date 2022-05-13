package com.digimoplus.foodninja.domain.model

import androidx.compose.material.SnackbarHostState


sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class WrongError(val message: String = "Wrong  Email or Password!") : DataState<Nothing>()
    data class SomeError(val message: String = "Some times Error !") : DataState<Nothing>()
    data class InvalidError(val message: String = "Invalid Email or Password!") :
        DataState<Nothing>()

    data class NetworkError(val message: String = "No Internet Connection!") : DataState<Nothing>()

}


