package com.digimoplus.foodninja.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun PayButton(
    @DrawableRes id: Int,
    isPressed: String,
    name: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(AppTheme.dimensions.grid_1),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.surface),
        border = if (isPressed == name) BorderStroke(1.dp, buttonEnabledGradient()) else null
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = id), contentDescription = "")
        }
    }
}