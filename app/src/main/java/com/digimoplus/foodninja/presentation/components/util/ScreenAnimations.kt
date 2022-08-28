package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween


fun screenEnterTransition(): EnterTransition {
    return slideInHorizontally(initialOffsetX = { 1000 },
        animationSpec = tween(durationMillis = 350, easing = LinearEasing))
}

fun screenExitTransition(): ExitTransition {
    return slideOutHorizontally(targetOffsetX = { -1000 },
        animationSpec = tween(durationMillis = 350, easing = LinearEasing))
}

fun screenPopEnterTransition(): EnterTransition {
    return slideInHorizontally(initialOffsetX = { -1000 },
        animationSpec = tween(durationMillis = 350, easing = LinearEasing))
}

fun screenPopExitTransition(): ExitTransition {
    return slideOutHorizontally(targetOffsetX = { 1000 },
        animationSpec = tween(durationMillis = 350, easing = LinearEasing))
}

fun screenSlideInVerticalTransition(): EnterTransition {
    return slideInVertically(initialOffsetY = { 1000 },
        animationSpec = tween(durationMillis = 350, easing = LinearEasing))
}

fun screenSlideOutVerticalTransition(): ExitTransition {
    return slideOutVertically(targetOffsetY = { 1000 },
        animationSpec = tween(durationMillis = 350, easing = LinearEasing))
}