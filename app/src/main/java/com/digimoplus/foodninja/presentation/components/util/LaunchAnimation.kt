package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

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

fun Modifier.animateAlpha(
    delayMillis: Int = 0,
    durationMillis: Int,
): Modifier = composed {
    val state = remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(Unit) {
        state.value = 1f
    }
    val alphaAnimation by animateFloatAsState(
        targetValue = state.value,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )

    this.alpha(alphaAnimation)
}


