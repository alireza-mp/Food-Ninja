package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
fun Modifier.animateToTop(
    durationMillis: Int = 500,
    delayMillis: Int = 300,
): Modifier = composed {
    val state = remember {
        mutableStateOf(AnimModel(0f, 300.dp))
    }

    val offsetAnimation: Dp by animateDpAsState(
        state.value.dp,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )

    LaunchedEffect(Unit) {
        state.value = AnimModel(1f, 0.dp)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = state.value.float,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )

    this
        .absoluteOffset(y = offsetAnimation)
        .alpha(alphaAnimation)

}

data class AnimModel(
    val float: Float,
    val dp: Dp,
)
