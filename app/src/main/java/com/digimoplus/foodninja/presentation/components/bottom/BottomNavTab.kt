package com.digimoplus.foodninja.presentation.components.bottom

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme


const val TAB_ICON_BASKET = R.drawable.ic_basket
const val TAB_ICON_HOME = R.drawable.ic_home
const val TAB_ICON_CHAT = R.drawable.ic_chat
const val TAB_ICON_PROFILE = R.drawable.ic_profile

@ExperimentalMaterialApi
@Composable
fun NavTab(state: Boolean, @DrawableRes id: Int, onClick: () -> Unit) {

    val model = remember {
        mutableStateOf(TabModel(
            50.dp,
            25f,
            0f,
            0.dp,
            0.5f
        ))
    }

    if (state) {
        model.value = TabModel(
            120.dp,
            35f,
            0.2f,
            50.dp,
            1f
        )
    } else {
        model.value = TabModel(
            50.dp,
            25f,
            0f,
            0.dp,
            0.5f
        )
    }

    val eAnimWith by animateDpAsState(
        targetValue = model.value.eWidth,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val eAnimCorner by animateFloatAsState(
        targetValue = model.value.eCorner,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val eAlpha by animateFloatAsState(
        targetValue = model.value.eSAlpha,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val iconAlpha by animateFloatAsState(
        targetValue = model.value.iAlpha,
        animationSpec = tween(
            durationMillis = 300,
        )
    )

    val enaem by animateDpAsState(
        targetValue = model.value.eName,
        animationSpec = tween(
            durationMillis = 300,
        )
    )



    Card(
        shape = RoundedCornerShape(eAnimCorner),
        modifier = Modifier
            .size(eAnimWith, 50.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.onPrimary.copy(alpha = eAlpha)),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(modifier = Modifier.alpha(iconAlpha), painter = painterResource(id = id),
                    contentDescription = "")
                Text(text = " Home",
                    style = AppTheme.typography.body1,
                    maxLines = 1,
                    color = Color.Black,
                    modifier = Modifier
                        .width(enaem)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NavTab(onClick: () -> Unit) {

    Card(
        backgroundColor = AppTheme.colors.surface,
        modifier = Modifier
            .size(50.dp, 50.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        elevation = 0.dp,
    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BadgeIcon(backgroundColor = Color(0xFFFF4B4B),
                    text = "3",
                    iconId = TAB_ICON_BASKET)
            }
        }
    }
}