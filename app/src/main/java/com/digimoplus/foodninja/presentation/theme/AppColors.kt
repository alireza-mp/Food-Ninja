package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.*
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


class AppColors(
    background: Color,
    titleText: Color,
    onTitleText: Color,
    primaryText: Color,
    primaryTextVariant: Color,
    primaryVariant: Color,
    surface: Color,
    onSurface: Color,
    primary: Color,
    onPrimary: Color,
    secondary: Color,
    onSecondary: Color,
    secondaryText: Color,
    isLight: Boolean
) {

    var isLight by mutableStateOf(isLight)
        internal set
    var background by mutableStateOf(background)
        private set
    var titleText by mutableStateOf(titleText)
        private set
    var onTitleText by mutableStateOf(onTitleText)
        private set
    var primaryText by mutableStateOf(primaryText)
        private set
    var primaryTextVariant by mutableStateOf(primaryTextVariant)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
        private set
    var surface by mutableStateOf(surface)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var primary by mutableStateOf(primary)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var secondaryText by mutableStateOf(secondaryText)
        private set

    fun copy(
        background: Color = this.background,
        titleText: Color = this.titleText,
        onTitleText: Color = this.onTitleText,
        primaryText: Color = this.primaryText,
        primaryTextVariant: Color = this.primaryTextVariant,
        primaryVariant: Color = this.primaryVariant,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        secondary: Color = this.secondary,
        onSecondary: Color = this.onSecondary,
        secondaryText: Color = this.secondaryText,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        background = background,
        titleText = titleText,
        onTitleText = onTitleText,
        primaryText = primaryText,
        primaryTextVariant = primaryTextVariant,
        primaryVariant = primaryVariant,
        surface = surface,
        onSurface = onSurface,
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryText = secondaryText,
        isLight = isLight
    )

    fun updateColorsFrom(other: AppColors) {
        background = other.background
        titleText = other.titleText
        onTitleText = other.onTitleText
        primaryText = other.primaryText
        primaryTextVariant = other.primaryTextVariant
        primaryVariant = other.primaryVariant
        surface = other.surface
        onSurface = other.onSurface
        primary = other.primary
        onPrimary = other.onPrimary
        secondary = other.secondary
        onSecondary = other.onSecondary
        secondaryText = other.secondaryText
        isLight = other.isLight
    }
}

internal val LocalColors = staticCompositionLocalOf { lightTheme() }