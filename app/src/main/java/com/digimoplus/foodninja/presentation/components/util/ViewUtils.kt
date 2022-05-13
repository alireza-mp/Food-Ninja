package com.digimoplus.foodninja.presentation.components.util

import android.content.Context
import android.widget.Toast
import androidx.compose.material.SnackbarHostState
import com.digimoplus.foodninja.domain.model.DataState

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

suspend fun DataState<Any>.showSnackBarError(snackBarHostState: SnackbarHostState) {
    when (this) {
        is DataState.WrongError -> {
            snackBarHostState.showSnackbar(message)
        }
        is DataState.InvalidError -> {
            snackBarHostState.showSnackbar(message)
        }
        is DataState.NetworkError -> {
            snackBarHostState.showSnackbar(message)
        }
        else -> {
            snackBarHostState.showSnackbar(DataState.SomeError().message)
        }
    }
}