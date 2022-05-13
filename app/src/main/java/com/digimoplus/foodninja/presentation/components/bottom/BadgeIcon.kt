package com.digimoplus.foodninja.presentation.components.bottom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R

@Composable
fun BadgeIcon(
    backgroundColor: Color,
    text: String,
    @DrawableRes iconId: Int,

    ) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(34.dp)) {
        Image(painter = painterResource(id = iconId),
            contentDescription = "")
        Card(shape = RoundedCornerShape(50f),
            border = BorderStroke(1.dp, Color.White),
            backgroundColor = backgroundColor,
            modifier = Modifier
                .size(15.dp)
                .align(
                    Alignment.TopEnd)) {
            Text(modifier = Modifier.matchParentSize(),
                color = Color.White,
                fontSize = 10.sp,
                text = text,
                textAlign = TextAlign.Center)
        }

    }
}