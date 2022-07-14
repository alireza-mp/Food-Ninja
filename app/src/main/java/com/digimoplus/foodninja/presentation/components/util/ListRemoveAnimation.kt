package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.removeAnimItem(
    alphaState: MutableState<Float>,
    heightState: MutableState<Dp>,
) = composed {


    val alphaAnimation by animateFloatAsState(
        targetValue = alphaState.value,
        animationSpec = tween(
            durationMillis = 250,
            delayMillis = 0
        )
    )
    val sizeAnim by animateDpAsState(
        targetValue = heightState.value,
        animationSpec = tween(
            durationMillis = 250,
            delayMillis = 250
        )
    )


    this
        .alpha(alphaAnimation)
        .height(sizeAnim)

}