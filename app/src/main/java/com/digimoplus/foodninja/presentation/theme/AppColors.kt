package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.*
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


class AppColors(
    primary: Color,
    secondary: Color,
    textSecondary: Color,
    error: Color,
    background: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var background by mutableStateOf(background)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var error by mutableStateOf(error)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textSecondary: Color = this.textSecondary,
        error: Color = this.error,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        secondary,
        textSecondary,
        error,
        background,
        isLight
    )

    // will be explained later
    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        secondary = other.secondary
        textSecondary = other.textSecondary
        background = other.background
        error = other.error
    }
}

internal val LocalColors = staticCompositionLocalOf { lightTheme() }