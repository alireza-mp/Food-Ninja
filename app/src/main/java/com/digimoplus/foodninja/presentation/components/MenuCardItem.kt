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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer


// menu card item // without animation // main content
@Composable
fun MenuCardItem(
    index: Int,
    model: Menu,
    count: Int,
    onClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(getPadding(index = index, count = count)),
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = AppTheme.colors.surface
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val image = loadPicture(url = model.imageUrl).value
            image?.let { img ->
                Card(modifier = Modifier.padding(
                    start = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent) {
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .animateAlpha(
                                delayMillis = 500,
                                durationMillis = 750)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = model.name,
                    style = AppTheme.typography.h7,
                    color = AppTheme.colors.titleText
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = model.restaurantName,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.onTitleText)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${model.price}$",
                modifier = Modifier.padding(end = 20.dp),
                style = AppTheme.typography.h4M,
                color = AppTheme.colors.primaryVariant)
        }

    }

}

// menu card item + animation // menuContent list
@Composable
fun MenuCardItem(
    index: Int,
    model: Menu,
    animationEnabled: Boolean,
    disableAnim: () -> Unit,
    onClick: () -> Unit,
) {

    if (animationEnabled && index < 6) {
        Box(modifier = Modifier.animateToTop(
            durationMillis = 300,
            delayMillis = getDelayMillis(index)
        )) {
            MenuItem(
                model = model,
                onClick = onClick,
            )
        }
    } else {
        disableAnim()
        MenuItem(
            model = model,
            onClick = onClick,
        )
    }

}

// private menu card item for menu list
@Composable
private fun MenuItem(
    model: Menu,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.padding(
            PaddingValues(top = 8.dp,
                bottom = 0.dp,
                end = 2.dp,
                start = 2.dp)
        ),
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = AppTheme.colors.surface
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val image = loadPicture(url = model.imageUrl).value
            image?.let { img ->
                Card(modifier = Modifier.padding(
                    start = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent) {
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .animateAlpha(
                                delayMillis = 500,
                                durationMillis = 750)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = model.name,
                    style = AppTheme.typography.h7,
                    color = AppTheme.colors.titleText
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = model.restaurantName,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.onTitleText)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${model.price}$",
                modifier = Modifier.padding(end = 20.dp),
                style = AppTheme.typography.h4M,
                color = AppTheme.colors.primaryVariant)
        }
    }
}

// menu item shimmer
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
                    start = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
                .size(80.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp,
                backgroundColor = Color.LightGray) {

            }

            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    modifier = Modifier.background(color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)),
                    text = "Green Noddle",
                    style = AppTheme.typography.h7,
                    color = Color.Transparent
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
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
                    .padding(end = 20.dp)
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
            PaddingValues(bottom = 8.dp, end = 2.dp, start = 2.dp)
        }
        count - 1 -> {
            PaddingValues(top = 8.dp,
                bottom = 100.dp,
                end = 2.dp,
                start = 2.dp)
        }
        else -> {
            PaddingValues(vertical = 8.dp, horizontal = 2.dp)
        }
    }
}

private fun getDelayMillis(index: Int): Int {
    return when (index) {
        0 -> 0
        1 -> 250
        2 -> 500
        3 -> 750
        4 -> 1000
        5 -> 1250
        else -> {
            0
        }
    }
}