package com.digimoplus.foodninja.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun CardUploadPhoto(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes id: Int,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.surface),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        onClick = onClick,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = id),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = title,
                style = AppTheme.typography.h7,
                color = AppTheme.colors.titleText,
                fontSize = 14.sp,
            )
        }

    }

}