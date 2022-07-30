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
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer

// Restaurant Card in home display
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(
    model: Restaurant,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(170.dp, 200.dp)
            .padding(top = 16.dp, bottom = 16.dp, end = 16.dp, start = 1.dp),
        onClick = onClick,
        backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .size(170.dp, 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val image = loadPicture(url = model.imageUrl).value

            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .animateAlpha(delayMillis = 500, durationMillis = 750),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(text = model.name,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(text = model.location,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText)
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
    }
}

//Restaurant card shimmer
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItemShimmer() {
    Card(
        modifier = Modifier
            .shimmer()
            .size(170.dp, 200.dp)
            .padding(top = 16.dp, bottom = 16.dp, end = 16.dp, start = 1.dp),
        onClick = {}, backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .size(100.dp),
                backgroundColor = Color.LightGray
            ) {}

            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                modifier = Modifier.background(color = Color.LightGray),
                text = "Vegan Resto",
                color = Color.Transparent,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(text = "12 Min",
                style = AppTheme.typography.body1,
                color = Color.Transparent,
                modifier = Modifier.background(color = Color.LightGray))
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
    }
}

// Restaurant card + animation
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(
    index: Int,
    model: Restaurant,
    animationEnabled: Boolean,
    disableAnim: () -> Unit,
    onClick: () -> Unit,
) {

    if (animationEnabled && index < 6) {

        RestaurantItem(
            modifier = Modifier.animateToTop(
                durationMillis = 300,
                delayMillis = getDelayMillis(index)
            ),
            model = model,
            onClick = onClick
        )
    } else {
        disableAnim()
        RestaurantItem(
            model = model,
            onClick = onClick
        )
    }

}

// getDelayMillis for animation restaurant card
private fun getDelayMillis(index: Int): Int {
    return when (index) {
        0 -> 0
        1 -> 0
        2 -> 300
        3 -> 300
        4 -> 600
        5 -> 600
        else -> {
            0
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun RestaurantItem(
    modifier: Modifier = Modifier,
    model: Restaurant,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(
                start = 14.dp,
                end = 14.dp,
                top = 12.dp,
                bottom = 0.dp
            ),
        onClick = onClick, backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val image = loadPicture(url = model.imageUrl).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .animateAlpha(delayMillis = 500, durationMillis = 750),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(text = model.name,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(text = model.location,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText)
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
    }
}

