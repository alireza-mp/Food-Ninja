package com.digimoplus.foodninja.presentation.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.util.NetworkImage
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer

// Restaurant Card in home display
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    model: Restaurant,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(paddingValues)
            .size(147.dp, 184.dp)
            .coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp
            ),
        onClick = onClick,
        backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(22.dp),
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            NetworkImage(url = model.imageUrl, size = 100.dp)

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = model.name,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = model.location,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText,
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
            )
        }
    }
}

//Restaurant card shimmer
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItemShimmer(paddingValues: PaddingValues) {
    Card(
        modifier = Modifier
            .shimmer()
            .padding(paddingValues)
            .size(147.dp, 184.dp),
        onClick = {}, backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Card(
                modifier = Modifier
                    .size(100.dp),
                backgroundColor = Color.LightGray
            ) {}
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                modifier = Modifier.background(color = Color.LightGray),
                text = "Vegan Resto",
                color = Color.Transparent,
                style = AppTheme.typography.h7
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = "12 Min",
                style = AppTheme.typography.body1,
                color = Color.Transparent,
                modifier = Modifier.background(color = Color.LightGray)
            )
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
    paddingValues: PaddingValues,
    disableAnim: () -> Unit,
    onClick: () -> Unit,
) {

    if (animationEnabled && index < 6) {

        RestaurantCardItem(
            modifier = Modifier.animateToTop(
                durationMillis = 300,
                delayMillis = getDelayMillis(index)
            ),
            model = model,
            onClick = onClick,
            paddingValues = paddingValues,
        )
    } else {
        disableAnim()
        RestaurantCardItem(
            model = model,
            onClick = onClick,
            paddingValues = paddingValues,
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
