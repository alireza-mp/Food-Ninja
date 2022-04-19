package com.digimoplus.foodninja.presentation.theme

import androidx.compose.ui.graphics.Color

//light colors
private val colorLightPrimary = Color(0xFFFFB400)
private val colorLightTextPrimary = Color(0xFF000000)
private val colorLightTextSecondary = Color(0xFF6C727A)
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightError = Color(0xFFD62222)

//dark colors
private val colorDarkPrimary = Color(0xFF673AB7)
private val colorDarkTextPrimary = Color(0xFFFF9800)
private val colorDarkTextSecondary = Color(0xFFFF5722)
private val colorDarkBackground = Color(0xFF000000)
private val colorDarkError = Color(0xFF8BC34A)

fun lightTheme(
    primary: Color = colorLightPrimary,
    secondary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError
): AppColors = AppColors(
    primary = primary,
    textSecondary = textSecondary,
    secondary = secondary,
    background = background,
    error = error,
    isLight = true
)

fun darkTheme(
    primary: Color = colorDarkPrimary,
    secondary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    error: Color = colorDarkError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = false
)