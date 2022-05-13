package com.digimoplus.foodninja.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.presentation.components.util.DEFAULT_RECIPE_IMAGE
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer


@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(index: Int, model: Restaurant) {

    Card(
        modifier = Modifier
            .padding(if (index != 0)
                PaddingValues(
                    vertical = AppTheme.dimensions.grid_2,
                    horizontal = AppTheme.dimensions.grid_1_5)
            else PaddingValues(
                start = 1.dp,
                top = AppTheme.dimensions.grid_2,
                bottom = AppTheme.dimensions.grid_2,
                end = AppTheme.dimensions.grid_1_5)
            ),
        onClick = {}, backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.grid_2_5,
                vertical = AppTheme.dimensions.grid_1_5),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val image = loadPicture(url = model.imageUrl,
                defaultImage = DEFAULT_RECIPE_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
            Text(text = model.name,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
            Text(text = model.location,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText)
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
        }
    }
}

// shimmer
@Composable
fun RestaurantCardItemShimmer(index: Int, visibility: Boolean) {

    Card(

        modifier = Modifier
            .shimmer()
            .padding(if (index != 0)
                PaddingValues(
                    vertical = AppTheme.dimensions.grid_2,
                    horizontal = AppTheme.dimensions.grid_1_5)
            else PaddingValues(
                start = 1.dp,
                top = AppTheme.dimensions.grid_2,
                bottom = AppTheme.dimensions.grid_2,
                end = AppTheme.dimensions.grid_1_5)
            ),
        backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.grid_2_5,
                vertical = AppTheme.dimensions.grid_1_5),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Card(
                modifier = Modifier
                    .size(100.dp),
                backgroundColor = Color.LightGray,
                elevation = 0.dp,
                shape = RoundedCornerShape(5.dp)
            ) {

            }

            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
            Text(modifier = Modifier.background(color = Color.LightGray),
                text = "Vegan Resto",
                color = Color.Transparent,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
            Text(modifier = Modifier.background(color = Color.LightGray),
                text = "12 Mins",
                style = AppTheme.typography.body1,
                color = Color.Transparent)
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
        }
    }

}