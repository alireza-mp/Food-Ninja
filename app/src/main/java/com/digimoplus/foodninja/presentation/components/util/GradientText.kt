package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import com.digimoplus.foodninja.presentation.theme.AppTheme

fun Modifier.textBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }

@Composable
fun gradientText(): Brush {
    return Brush.horizontalGradient(
        listOf(
            AppTheme.colors.onPrimary,
            AppTheme.colors.primary
        )
    )
}