package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R

private val bentonSansBlack = FontFamily(
    Font(R.font.benton_sans_black, FontWeight.Normal)
)


data class AppTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = bentonSansBlack,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp
    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = bentonSansBlack,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    val body: TextStyle = TextStyle(
        fontFamily = bentonSansBlack,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = bentonSansBlack,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = bentonSansBlack,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }