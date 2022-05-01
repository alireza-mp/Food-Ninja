package com.digimoplus.foodninja.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun CardUploadPhoto(
    title: String,
    @DrawableRes id: Int,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.surface),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.dimensions.grid_3),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = id),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_2))
            Text(
                text = title,
                style = AppTheme.typography.h7,
                color = AppTheme.colors.titleText,
            )
        }

    }

}