package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.ui.splash.SplashPage
import com.google.accompanist.navigation.animation.composable


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