package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

val sw360DTypography = AppTypography(
    h1 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    h7 = TextStyle(
        fontFamily = bentonSansMedium,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    descBold = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        letterSpacing = 0.15.sp
    ),
    body = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.7.sp
    ),
    body1 = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    )
)

val smallTypography = AppTypography(
    h1 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 62.5.sp,
        letterSpacing = (-0.975).sp
    ),
    h2 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 39.sp,
        letterSpacing = (-0.325).sp
    ),
    h3 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 31.5.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp,
        letterSpacing = 0.1625.sp
    ),
    h4M = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 19.5.sp,
        letterSpacing = 0.1625.sp
    ),
    h5 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 15.5.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        letterSpacing = 0.975.sp
    ),
    h7 = TextStyle(
        fontFamily = bentonSansMedium,
        fontWeight = FontWeight.Light,
        fontSize = 10.4.sp,
        letterSpacing = 0.0975.sp
    ),
    descBold = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 7.8.sp,
        letterSpacing = 0.0975.sp
    ),
    body = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 11.5.sp,
        letterSpacing = 0.454.sp
    ),
    body1 = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 9.5.sp,
        letterSpacing = 0.325.sp
    ),
    body2 = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        letterSpacing = 0.16.sp
    ),
    button = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 9.5.sp,
        letterSpacing = 0.81.sp
    ),
    caption = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        letterSpacing = 0.26.sp
    ),
    overline = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 6.5.sp,
        letterSpacing = 0.97.sp
    )
)


val mediumTypography = AppTypography(
    h1 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 80.sp,
        letterSpacing = (-1.25).sp
    ),
    h2 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 50.sp,
        letterSpacing = (-0.4).sp
    ),
    h3 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 39.5.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = 0.20.sp
    ),
    h4M = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 24.7.sp,
        letterSpacing = 0.20.sp
    ),
    h5 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 16.5.sp,
        letterSpacing = 0.12.sp
    ),
    h7 = TextStyle(
        fontFamily = bentonSansMedium,
        fontWeight = FontWeight.Light,
        fontSize = 13.20.sp,
        letterSpacing = 0.12.sp
    ),
    descBold = TextStyle(
        fontFamily = bentonSansBlod,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        letterSpacing = 0.12.sp
    ),
    body = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.57.sp
    ),
    body1 = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 11.5.sp,
        letterSpacing = 0.4.sp
    ),
    body2 = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.2.sp
    ),
    button = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 11.5.sp,
        letterSpacing = 1.sp
    ),
    caption = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.325.sp
    ),
    overline = TextStyle(
        fontFamily = bentonSansRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 8.2.sp,
        letterSpacing = 1.25.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }