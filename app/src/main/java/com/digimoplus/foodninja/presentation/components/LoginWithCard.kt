@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun LoginWithCard(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    title: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp,
        backgroundColor = AppTheme.colors.surface,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = iconId),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.padding(end = 12.dp))
            Text(
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
            )
        }
    }
}