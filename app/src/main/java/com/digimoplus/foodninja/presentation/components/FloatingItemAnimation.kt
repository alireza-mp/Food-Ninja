package com.digimoplus.foodninja.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FloatingImageAnimation(
    modifier: Modifier = Modifier,
    randomRange: IntRange,
    content: @Composable (Float) -> Unit,
) {

    val widthState = remember { mutableStateOf((randomRange).random().dp) }
    val heightState = remember { mutableStateOf((randomRange).random().dp) }
    val alphaState = remember { mutableStateOf(0f) }

    var weidthDefaultDuration = 1000
    val width by animateDpAsState(
        targetValue = widthState.value,
        animationSpec = tween(
            durationMillis = weidthDefaultDuration,
            delayMillis = 0,
            easing = LinearEasing,
        )
    )

    var heightDefaultDuration = 1000
    val height by animateDpAsState(
        targetValue = heightState.value,
        animationSpec = tween(
            durationMillis = heightDefaultDuration,
            delayMillis = 0,
            easing = LinearEasing,
        )
    )

    val alpha by animateFloatAsState(
        targetValue = alphaState.value,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0,
            easing = LinearEasing
        )
    )


    // rw : new offset value width
    // rh : new offset value height
    LaunchedEffect(Unit) {
        alphaState.value = 1f
        while (true) {
            delay(1000)
            val rw = (10..20).random()
            weidthDefaultDuration = rw * 30
            val rh = (10..20).random()
            heightDefaultDuration = rh * 30
            widthState.value = rw.dp
            heightState.value = rh.dp
        }
    }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset(width, height),
            contentAlignment = Alignment.Center
        ) {
            content(alpha)
        }
    }
}
