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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.util.DEFAULT_RESTAURANT_CARD_ITEM_IMAGE
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content.HomeRestaurantViewModel
import com.valentinilk.shimmer.shimmer

// Restaurant Card in home display
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(index: Int, model: Restaurant) {
    Card(
        modifier = Modifier
            .size(170.dp, 200.dp)
            .padding(getCardPadding(index)),
        onClick = {}, backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.grid_2_5,
                vertical = AppTheme.dimensions.grid_1_5),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val image = loadPicture(url = model.imageUrl,
                defaultImage = DEFAULT_RESTAURANT_CARD_ITEM_IMAGE).value
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

//Restaurant card shimmer
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItemShimmer(index: Int) {
    Card(
        modifier = Modifier
            .shimmer()
            .size(170.dp, 200.dp)
            .padding(getCardPadding(index)),
        onClick = {}, backgroundColor = AppTheme.colors.surface,
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
                backgroundColor = Color.LightGray
            ) {}

            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
            Text(
                modifier = Modifier.background(color = Color.LightGray),
                text = "Vegan Resto",
                color = Color.Transparent,
                style = AppTheme.typography.h7)
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
            Text(text = "12 Min",
                style = AppTheme.typography.body1,
                color = Color.Transparent,
                modifier = Modifier.background(color = Color.LightGray))
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
        }
    }
}

//getCardPadding
@Composable
private fun getCardPadding(index: Int): PaddingValues {
    return if (index != 0)
        PaddingValues(
            vertical = AppTheme.dimensions.grid_2,
            horizontal = AppTheme.dimensions.grid_1_5)
    else PaddingValues(
        start = 1.dp,
        top = AppTheme.dimensions.grid_2,
        bottom = AppTheme.dimensions.grid_2,
        end = AppTheme.dimensions.grid_1_5)

}


// Restaurant card + animation
@ExperimentalMaterialApi
@Composable
fun RestaurantCardItem(
    index: Int,
    viewModel: HomeRestaurantViewModel,
    model: Restaurant,
    animationEnabled: Boolean,
    disableAnim: () -> Unit,
    onClick: () -> Unit,
) {

    if (animationEnabled && index < 6) {
        Box(modifier = Modifier.animateToTop(
            dpSize = 20.dp,
            durationMillis = 300,
            delayMillis = getDelayMillis(index))) {
            RestaurantItem(padding =
            PaddingValues(
                start = AppTheme.dimensions.grid_2,
                end = AppTheme.dimensions.grid_2,
                top = AppTheme.dimensions.grid_1_5,
                bottom = restaurantListPaddingBottom(index = index,
                    viewModel = viewModel)
            ), model = model, onClick = onClick)
        }
    } else {
        disableAnim()
        RestaurantItem(padding =
        PaddingValues(
            start = AppTheme.dimensions.grid_2,
            end = AppTheme.dimensions.grid_2,
            top = AppTheme.dimensions.grid_1_5,
            bottom = restaurantListPaddingBottom(index = index,
                viewModel = viewModel)
        ), model = model, onClick = onClick)
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
    padding: PaddingValues,
    model: Restaurant,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(padding),
        onClick = onClick, backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.grid_2_5,
                vertical = AppTheme.dimensions.grid_1_5),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val image = loadPicture(url = model.imageUrl,
                defaultImage = DEFAULT_RESTAURANT_CARD_ITEM_IMAGE).value
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

@Composable
private fun restaurantListPaddingBottom(index: Int, viewModel: HomeRestaurantViewModel): Dp {
    return when {
        viewModel.checkIsLastPage() && (index == viewModel.restaurantAllList.size - 1) -> {
            100.dp
        }
        index == viewModel.restaurantAllList.size - 1 -> {
            150.dp
        }
        else -> {
            0.dp
        }
    }
}
