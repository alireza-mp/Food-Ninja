package com.digimoplus.foodninja.presentation.components.main_pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.CustomSnackBar
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun PageMainBackgroundImage(
    @DrawableRes lightBackground: Int = R.drawable.background_light,
    @DrawableRes darkBackground: Int = R.drawable.background_dark,
    snackBarState: SnackbarHostState? = null,
    paddingValues: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
    ) {
        Image(
            painter = painterResource(id = if (AppTheme.colors.isLight) lightBackground else darkBackground),
            contentDescription = "",
            alignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content()
        }
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