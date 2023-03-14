@file:OptIn(ExperimentalMaterialApi::class)

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
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.presentation.components.util.NetworkImage
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.valentinilk.shimmer.shimmer


// menu card item // without animation // main content
@Composable
fun MenuCardItem(
    model: Menu,
    paddingValues: PaddingValues,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(paddingValues)
            .height(87.dp)
            .fillMaxWidth()
            .coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
        onClick = { onClick() },
        shape = RoundedCornerShape(22.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp,
    ) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 12.dp, bottom = 12.dp, start = 11.dp)
            ) {
                NetworkImage(url = model.imageUrl, size = 64.dp)
            }

            Column(modifier = Modifier.padding(start = 14.dp)) {
                Text(
                    text = model.name,
                    style = AppTheme.typography.h7,
                    color = AppTheme.colors.titleText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = model.restaurantName,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.onTitleText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${model.price}$",
                modifier = Modifier.padding(end = 20.dp),
                style = AppTheme.typography.h4M,
                color = AppTheme.colors.primaryVariant,
                fontSize = 22.sp,
                fontWeight = FontWeight.W400,
            )
        }

    }

}

// menu card item + animation // menuContent list
@Composable
fun AnimatedMenuCardItem(
    index: Int,
    model: Menu,
    animationEnabled: Boolean,
    paddingValues: PaddingValues,
    disableAnim: () -> Unit,
    onClick: () -> Unit,
) {

    if (animationEnabled && index < 6) {
        Box(
            modifier = Modifier.animateToTop(
                durationMillis = 300,
                delayMillis = getDelayMillis(index)
            )
        ) {
            MenuCardItem(
                model = model,
                paddingValues = paddingValues,
                onClick = onClick,
            )
        }
    } else {
        disableAnim()
        MenuCardItem(
            model = model,
            paddingValues = paddingValues,
            onClick = onClick,
        )
    }

}


// menu item shimmer
@Composable
fun MenuCardItemShimmer(
    paddingValues: PaddingValues,
) {
    Card(
        modifier = Modifier
            .padding(
                paddingValues
            )
            .height(87.dp)
            .shimmer(),
        shape = RoundedCornerShape(22.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Card(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp, start = 11.dp)
                    .size(64.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp,
                backgroundColor = Color.LightGray
            ) {

            }

            Column(modifier = Modifier.padding(start = 14.dp)) {
                Text(
                    modifier = Modifier.background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    ),
                    text = "Green Noddle",
                    style = AppTheme.typography.h7,
                    color = Color.Transparent
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    modifier = Modifier.background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    ),
                    text = "NoodleHome",
                    style = AppTheme.typography.body1,
                    color = Color.Transparent
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$15",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp)),
                style = AppTheme.typography.h4M,
                color = Color.Transparent
            )
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