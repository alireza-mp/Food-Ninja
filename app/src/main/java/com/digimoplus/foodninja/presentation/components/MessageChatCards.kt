package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun SendMessage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
    ) {
        Card(modifier = Modifier.align(Alignment.CenterEnd), shape = RoundedCornerShape(15.dp)) {
            Box(
                modifier = Modifier
                    .background(brush = gradientText())
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                    text = text,
                    color = Color.White,
                    lineHeight = 20.sp,
                    style = AppTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun ReceiveMessage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Card(
            modifier = Modifier.align(Alignment.CenterStart),
            backgroundColor = AppTheme.colors.secondarySurface,
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                lineHeight = 20.sp,
                text = text,
                color = Color.Black,
                style = AppTheme.typography.body1
            )
        }
    }
}

@Composable
fun RoomMessage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            backgroundColor = AppTheme.colors.secondarySurface,
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                text = text,
                color = Color.Black,
                style = AppTheme.typography.body2
            )
        }
    }
}