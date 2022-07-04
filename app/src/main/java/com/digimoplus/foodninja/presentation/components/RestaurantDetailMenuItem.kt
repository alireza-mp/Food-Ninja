@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.model.RestoDetailMenu
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.loadPictureNoneDefault
import com.digimoplus.foodninja.presentation.theme.AppTheme


// item card in restaurantDetail.kt
@Composable
fun RestaurantDetailMenuItem(
    model: RestoDetailMenu,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(180.dp, 200.dp)
            .padding(bottom = 10.dp, top = 10.dp, end = 10.dp, start = 1.dp),
        onClick = onClick,
        backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 5.dp,
                vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val image = loadPictureNoneDefault(url = model.imageUrl).value
            val imageAnim = remember {
                mutableStateOf(0f)
            }
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(90.dp)
                        .animateAlpha(state = imageAnim, delayMillis = 500, durationMillis = 750),
                    contentScale = ContentScale.Crop
                )
            }
            LaunchedEffect(Unit) {
                imageAnim.value = 1f
            }
            Spacer(modifier = Modifier.padding(top = 22.dp))
            Text(text = model.name,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(text = model.price,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText)
            Spacer(modifier = Modifier.padding(top = 4.dp))
        }
    }
}