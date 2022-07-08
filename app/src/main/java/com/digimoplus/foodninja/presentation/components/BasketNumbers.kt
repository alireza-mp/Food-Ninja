@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    name: String,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(start = 16.dp))
        Text(
            text = name,
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h7
        )
        Spacer(Modifier.weight(1f))
        Card(
            modifier = Modifier.size(36.dp),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(10.dp),
            onClick = onMinus
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = buttonEnabledGradient()),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus),
                    modifier = Modifier.size(18.dp),
                    contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.padding(end = 12.dp))
        Text(
            text = text,
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h7,
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(end = 12.dp))
        Card(
            modifier = Modifier.size(36.dp),
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
                    modifier = Modifier.size(18.dp),
                    contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
    }
}