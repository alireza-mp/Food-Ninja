package com.digimoplus.foodninja.domain.util

import android.content.Context
import android.widget.Toast
import androidx.compose.material.SnackbarHostState

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

suspend fun DataState<Any>.showSnackBarError(snackBarHostState: SnackbarHostState?) {
    when (this) {
        is DataState.WrongLoginError -> {
            snackBarHostState?.showSnackbar(message)
        }
        is DataState.InvalidLoginError -> {
            snackBarHostState?.showSnackbar(message)
        }
        is DataState.NetworkError -> {
            snackBarHostState?.showSnackbar(message)
        }
        else -> {
            snackBarHostState?.showSnackbar(DataState.SomeError().message)
        }
    }
}
