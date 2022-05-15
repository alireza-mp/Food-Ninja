package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
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
    dpSize: Dp,
    durationMillis: Int = 500,
    delayMillis: Int = 300,
): Modifier = composed {
    val state = remember {
        mutableStateOf(AnimModel(0f, dpSize))
    }

    LaunchedEffect(Unit) {
        state.value = AnimModel(1f, 0.dp)
    }

    val paddingAnimation by animateDpAsState(
        targetValue = state.value.dp,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )
    val alphaAnimation by animateFloatAsState(
        targetValue = state.value.float,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )

    this
        .padding(top = paddingAnimation)
        .alpha(alphaAnimation)

}

data class AnimModel(
    val float: Float,
    val dp: Dp,
)
