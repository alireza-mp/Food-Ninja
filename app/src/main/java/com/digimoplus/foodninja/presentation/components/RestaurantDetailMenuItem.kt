@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.domain.model.RestaurantDetailMenu
import com.digimoplus.foodninja.presentation.components.util.NetworkImage
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.theme.AppTheme


// item card in restaurantDetail.kt
@Composable
fun RestaurantDetailMenuItem(
    model: RestaurantDetailMenu,
    padding: PaddingValues,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(padding)
            .size(157.dp, 171.dp)
            .coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
        onClick = onClick,
        elevation = 0.dp,
        backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(22.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(top = 25.dp))

            NetworkImage(url = model.imageUrl, size = 71.dp)

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = model.name,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "${model.price}$",
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText,
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
            )
        }
    }
}
