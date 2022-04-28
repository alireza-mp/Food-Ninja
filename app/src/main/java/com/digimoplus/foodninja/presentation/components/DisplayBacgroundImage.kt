package com.digimoplus.foodninja.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun DisplayBackgroundImage(
    @DrawableRes lightBackground: Int = R.drawable.background_light,
    @DrawableRes darkBackground: Int = R.drawable.background_dark,
    snackBarState: SnackbarHostState? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = if (AppTheme.colors.isLight) lightBackground else darkBackground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        content()
        snackBarState?.let { snackBarState ->
            CustomSnackBar(
                snackBarHostState = snackBarState,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                snackBarState.currentSnackbarData?.dismiss()
            }
        }
    }
}