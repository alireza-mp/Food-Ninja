@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun BasketNumbers(
    text: String,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(
                    color = AppTheme.colors.onPrimary.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onMinus
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_minus),
                modifier = Modifier
                    .width(12.dp),
                contentDescription = null)

        }

        Spacer(modifier = Modifier.padding(end = 6.dp))
        Text(
            text = text,
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h7,
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(end = 6.dp))
        Card(
            modifier = Modifier.size(30.dp),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(10.dp),
            onClick = onPlus
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = buttonEnabledGradient()),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    modifier = Modifier.size(12.dp),
                    contentDescription = null)
            }
        }
    }
}