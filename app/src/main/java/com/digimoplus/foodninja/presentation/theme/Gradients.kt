package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush


@Composable
fun buttonGradient(): Brush {
    return Brush.horizontalGradient(
        listOf(
            AppTheme.colors.onPrimary,
            AppTheme.colors.primary
        )
    )
}


