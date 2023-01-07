package com.digimoplus.foodninja.domain.util

import android.graphics.Bitmap
import androidx.compose.material.SnackbarHostState
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

suspend fun DataState<Any>.showSnackBarError(snackBarHostState: SnackbarHostState?) {
    when (this) {
        is DataState.Loading -> {
            // don't show
        }
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

fun Bitmap.toInputStream(): InputStream {
    val bos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, bos)
    val bitmapData = bos.toByteArray()
    return ByteArrayInputStream(bitmapData)
}
