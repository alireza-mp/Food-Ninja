package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun buttonGradient(): Brush {
    return Brush.horizontalGradient(
        listOf(
            AppTheme.colors.onPrimary,
            AppTheme.colors.primary
        )
    )
}


