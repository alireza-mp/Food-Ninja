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
import com.digimoplus.foodninja.presentation.components.util.dps
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
        onClick = onClick,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(top = 16.dps).size(35.dps),
                painter = painterResource(id = id),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.padding(top = 4.dps))
            Text(
                text = title,
                style = AppTheme.typography.h7,
                color = AppTheme.colors.titleText,
                modifier =Modifier.padding(bottom = 16.dps)
            )
        }

    }

}