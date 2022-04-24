package com.digimoplus.foodninja.presentation.theme

import android.util.Size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

}

@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    content: @Composable () -> Unit
) {

    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides getDimensions(screenHeightDp),
        LocalTypography provides getTypography(screenHeightDp)

    ) {
        content()
    }
}

fun getDimensions(height: Int): AppDimensions {
    return when {
        height > 733 -> sw360Dimensions
        height in 641..732 -> mediumDimensions
        height < 640 -> smallDimensions
        else -> sw360Dimensions
    }
}

fun getTypography(height: Int): AppTypography {
    return when {
        height > 733 -> sw360DTypography
        height in 641..732 -> mediumTypography
        height < 640 -> smallTypography
        else -> sw360DTypography
    }
}

fun isDark(state: Boolean): AppColors {
    return if (state) return darkTheme() else lightTheme()
}