package com.digimoplus.foodninja.presentation.theme

import androidx.compose.ui.graphics.Color

//light colors
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightTitleText = Color(0xFF000000)
private val colorLightOnTitleText = Color(0xFF3B3B3B)
private val colorLightPrimaryText = Color(0xFF15BE77)
private val colorLightPrimaryTextVariant = Color(0xFFFF7C32) // page 1.16
private val colorLightPrimaryVariant = Color(0xFFFF7C32) // page 1.32
private val colorLightSurface = Color(0xFFFFFFFF)  // page 1.19
private val colorLightOnSurface = Color(0xFFF4F4F4) // page 1.4
private val colorLightPrimary = Color(0xFF15BE77)
private val colorLightOnPrimary = Color(0xFF53E88B)
private val colorLightSecondary = Color(0xFFDA6317)
private val colorLightOnSecondary = Color(0xFFF9A84D)
private val colorLightSecondaryText = Color(0xAADA6317)

//dark colors
private val colorDarkBackground = Color(0xFF0D0D0D)
private val colorDarkTitleText = Color(0xFFFFFFFF)
private val colorDarkOnTitleText = Color(0xAAFFFFFF)
private val colorDarkPrimaryText = Color(0xFF15BE77)
private val colorDarkPrimaryTextVariant = Color(0xFFFF7C32) // page 1.16
private val colorDarkPrimaryVariant = Color(0xFFFF7C32) // page 1.32
private val colorDarkSurface = Color(0xFFF4F4F4)  // page 1.19
private val colorDarkOnSurface = Color(0xFFF4F4F4)  // page 1.4
private val colorDarkPrimary = Color(0xFF15BE77)
private val colorDarkOnPrimary = Color(0xFF53E88B)
private val colorDarkSecondary = Color(0xFFFFFFFF)
private val colorDarkOnSecondary = Color(0xFFF4F4F4)
private val colorDarkSecondaryText = Color(0xAAFFFFFF)


fun lightTheme(
    background: Color = colorLightBackground,
    titleText: Color = colorLightTitleText,
    onTitleText: Color = colorLightOnTitleText,
    primaryText: Color = colorLightPrimaryText,
    primaryTextVariant: Color = colorLightPrimaryTextVariant,
    primaryVariant: Color = colorLightPrimaryVariant,
    surface: Color = colorLightSurface,
    onSurface: Color = colorLightOnSurface,
    primary: Color = colorLightPrimary,
    onPrimary: Color = colorLightOnPrimary,
    secondary: Color = colorLightSecondary,
    onSecondary: Color = colorLightOnSecondary,
    secondaryText: Color = colorLightSecondaryText,
    isLight: Boolean = true
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

fun darkTheme(
    background: Color = colorDarkBackground,
    titleText: Color = colorDarkTitleText,
    onTitleText: Color = colorDarkOnTitleText,
    primaryText: Color = colorDarkPrimaryText,
    primaryTextVariant: Color = colorDarkPrimaryTextVariant,
    primaryVariant: Color = colorDarkPrimaryVariant,
    surface: Color = colorDarkSurface,
    onSurface: Color = colorDarkOnSurface,
    primary: Color = colorDarkPrimary,
    onPrimary: Color = colorDarkOnPrimary,
    secondary: Color = colorDarkSecondary,
    onSecondary: Color = colorDarkOnSecondary,
    secondaryText: Color = colorDarkSecondaryText,
    isLight: Boolean = false
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