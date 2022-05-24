package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.ehsanmsz.mszprogressindicator.progressindicator.BallBeatProgressIndicator

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    gradient: Brush,
    text: String,
    textColor: Color,
    padding: PaddingValues = PaddingValues(),
    loading: Boolean = false,
    onClick: () -> Unit = {},
) {
    Button(
        enabled = !loading,
        modifier = modifier.padding(padding),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(15.dp),
        onClick = { onClick() })
    {
        Box(
            modifier = modifier
                .background(gradient)
                .padding(
                    horizontal = AppTheme.dimensions.grid_3_5,
                    vertical = AppTheme.dimensions.grid_1
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = AppTheme.dimensions.grid_1_5,
                    horizontal = AppTheme.dimensions.grid_3_5
                ),
                text = text,
                color = if (loading) Color.Transparent else textColor,
                style = AppTheme.typography.h7,
            )
            if (loading) {
                BallBeatProgressIndicator(color = Color.White)
            }
        }
    }
}

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    enableGradient: Brush,
    disableGradient: Brush,
    text: String,
    textColor: Color,
    enabled: Boolean,
    border: BorderStroke? = null,
    padding: PaddingValues = PaddingValues(),
    onClick: () -> Unit = {},
) {
    Button(
        border = border,
        enabled = enabled,
        modifier = modifier.padding(padding),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(15.dp),
        onClick = { onClick() })
    {
        Box(
            modifier = modifier
                .background(if (enabled) enableGradient else disableGradient)
                .padding(
                    horizontal = AppTheme.dimensions.grid_3_5,
                    vertical = AppTheme.dimensions.grid_1
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = AppTheme.dimensions.grid_1_5,
                    horizontal = AppTheme.dimensions.grid_3_5
                ),
                text = text,
                color = textColor,
                style = AppTheme.typography.h7,
            )
        }
    }
}
