package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha

fun Modifier.animateAlpha(
    state: MutableState<Float>,
    delayMillis: Int = 0,
    durationMillis: Int,
): Modifier = composed {

    val alphaAnimation by animateFloatAsState(
        targetValue = state.value,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )

    this.alpha(alphaAnimation)
}


