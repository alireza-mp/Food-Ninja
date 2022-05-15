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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.util.DEFAULT_RECIPE_IMAGE
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer

// Restaurant Card in home display
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


// Restaurant card in search  + animation
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(
    index: Int,
    model: Restaurant,
    count: Int,
    animationEnabled: Boolean,
    disableAnim: () -> Unit,
) {

    if (animationEnabled && index < 8) {
        Box(modifier = Modifier.animateToTop(
            dpSize = 20.dp,
            durationMillis = 300,
            delayMillis = getDelayMillis(index))) {

            RestaurantItem(index = index, count = count, model = model)
        }
    } else {
        disableAnim()
        RestaurantItem(index = index, count = count, model = model)
    }

}

//Restaurant card shimmer
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

// getDelayMillis for animation restaurant card
fun getDelayMillis(index: Int): Int {
    return when (index) {
        0 -> 0
        1 -> 0
        2 -> 300
        3 -> 300
        4 -> 600
        5 -> 600
        6 -> 900
        7 -> 900
        else -> {
            0
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun RestaurantItem(index: Int, count: Int, model: Restaurant) {
    Card(
        modifier = Modifier
            .padding(if (index != count - 1) {
                PaddingValues(
                    vertical = AppTheme.dimensions.grid_2,
                    horizontal = AppTheme.dimensions.grid_1_5)
            } else {
                PaddingValues(
                    start = AppTheme.dimensions.grid_2,
                    end = AppTheme.dimensions.grid_2,
                    top = AppTheme.dimensions.grid_1_5,
                    bottom = 100.dp)
            }),
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

