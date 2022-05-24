package com.digimoplus.foodninja.presentation.components.util

import android.hardware.lights.Light
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun buttonEnabledGradient(): Brush {
    return Brush.horizontalGradient(
        listOf(
            AppTheme.colors.onPrimary,
            AppTheme.colors.primary
        )
    )
}


@Composable
fun buttonDisabledGradient(): Brush {
    return Brush.horizontalGradient(
        listOf(
            AppTheme.colors.surface,
            AppTheme.colors.surface
        )
    )
}

@Composable
fun searchChipsGradient(isLight: Boolean): Brush {
    return if (isLight) {
        Brush.horizontalGradient(
            listOf(
                AppTheme.colors.secondary,
                AppTheme.colors.secondary
            )
        )
    } else {
        Brush.horizontalGradient(
            listOf(
                AppTheme.colors.onPrimary,
                AppTheme.colors.primary
            )
        )
    }
}


