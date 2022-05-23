package com.digimoplus.foodninja.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content.HomeRestaurantViewModel

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

@Composable
fun restaurantListPaddingBottom(index: Int, viewModel: HomeRestaurantViewModel): Dp {
    return when {
        viewModel.checkIsLastPage() && (index == viewModel.restaurantAllList.size - 1) -> {
            100.dp
        }
        index == viewModel.restaurantAllList.size - 1 -> {
            150.dp
        }
        else -> {
            0.dp
        }
    }
}