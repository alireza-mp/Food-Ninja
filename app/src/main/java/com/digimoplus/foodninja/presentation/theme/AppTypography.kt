package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R

private val bentonSansBlod = FontFamily(
    Font(R.font.benton_sans_bold, FontWeight.Light)
)

private val bentonSansRegular = FontFamily(
    Font(R.font.benton_sans_regular, FontWeight.Normal)
)
private val bentonSansMedium = FontFamily(
    Font(R.font.benton_sans_medium, FontWeight.Normal)
)


data class AppTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    val h3: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    val h4: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),
    val h4M: TextStyle = TextStyle(
        fontFamily = bentonSansMedium,
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        letterSpacing = 0.25.sp
    ),
    val h5: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    val h6: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    val h7: TextStyle = TextStyle(
        fontFamily = bentonSansMedium,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    val descBold: TextStyle = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        letterSpacing = 0.15.sp
    ),
    val body: TextStyle = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.7.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    val body2: TextStyle = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.25.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    val overline: TextStyle = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }