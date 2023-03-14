package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = modifier
            .padding(padding)
            .size(157.dp, 57.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        onClick = { onClick() })
    {
        Box(
            modifier = Modifier
                .background(gradient)
                .size(157.dp, 57.dp)
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.Center
        ) {
            Text(
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
        modifier = modifier
            .padding(padding),
        enabled = enabled,
        border = if (enabled) null else border,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(15.dp),
        onClick = { onClick() })
    {
        Box(
            modifier = modifier
                .background(if (enabled) enableGradient else disableGradient)
                .size(157.dp, 57.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                style = AppTheme.typography.h7,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
            )
        }
    }
}
