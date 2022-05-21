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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.presentation.components.util.DEFAULT_RESTAURANT_CARD_ITEM_IMAGE
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer


@ExperimentalMaterialApi
@Composable
fun MenuCardItem(index: Int, model: Menu, count: Int, onClick: () -> Unit) {

    Card(
        modifier = Modifier.padding(getPadding(index = index, count = count)),
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = AppTheme.colors.surface
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val image = loadPicture(url = model.imageUrl,
                defaultImage = DEFAULT_RESTAURANT_CARD_ITEM_IMAGE).value
            image?.let { img ->
                Card(modifier = Modifier.padding(
                    start = AppTheme.dimensions.grid_1_5,
                    top = AppTheme.dimensions.grid_2,
                    bottom = AppTheme.dimensions.grid_2),
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent) {
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(modifier = Modifier.padding(start = AppTheme.dimensions.grid_2_5)) {
                Text(
                    text = model.name,
                    style = AppTheme.typography.h7,
                    color = AppTheme.colors.titleText
                )
                Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
                Text(
                    text = model.restaurantName,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.onTitleText)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = model.price,
                modifier = Modifier.padding(end = AppTheme.dimensions.grid_2_5),
                style = AppTheme.typography.h4M,
                color = AppTheme.colors.primaryVariant)
        }

    }

}

@Composable
fun MenuCardItemShimmer(index: Int, count: Int) {
    Card(
        modifier = Modifier
            .padding(getPadding(index = index, count = count))
            .shimmer(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Card(modifier = Modifier
                .padding(
                    start = AppTheme.dimensions.grid_1_5,
                    top = AppTheme.dimensions.grid_2,
                    bottom = AppTheme.dimensions.grid_2)
                .size(80.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp,
                backgroundColor = Color.LightGray) {

            }

            Column(modifier = Modifier.padding(start = AppTheme.dimensions.grid_2_5)) {
                Text(
                    modifier = Modifier.background(color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)),
                    text = "Green Noddle",
                    style = AppTheme.typography.h7,
                    color = Color.Transparent
                )
                Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_0_5))
                Text(
                    modifier = Modifier.background(color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)),
                    text = "NoodleHome",
                    style = AppTheme.typography.body1,
                    color = Color.Transparent)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$15",
                modifier = Modifier
                    .padding(end = AppTheme.dimensions.grid_2_5)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp)),
                style = AppTheme.typography.h4M,
                color = Color.Transparent)
        }

    }

}

@Composable
private fun getPadding(index: Int, count: Int): PaddingValues {
    return when (index) {

        0 -> {
            PaddingValues(bottom = AppTheme.dimensions.grid_1, end = 2.dp, start = 2.dp)
        }
        count - 1 -> {
            PaddingValues(top = AppTheme.dimensions.grid_1,
                bottom = 100.dp,
                end = 2.dp,
                start = 2.dp)
        }
        else -> {
            PaddingValues(vertical = AppTheme.dimensions.grid_1, horizontal = 2.dp)
        }
    }
}