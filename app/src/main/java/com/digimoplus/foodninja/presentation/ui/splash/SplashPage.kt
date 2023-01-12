package com.digimoplus.foodninja.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashPage(navController: NavController) {

    val viewModel: SplashViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        checkUserStatus(
            viewModel = viewModel,
            navController = navController,
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        // background image
        Image(
            modifier = Modifier
                .fillMaxSize()
                .animateAlpha(
                    durationMillis = 500
                ),
            painter = painterResource(id = if (AppTheme.colors.isLight) R.drawable.background_light else R.drawable.background_dark),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        // ball progress
        if (!viewModel.retryVisibility.value) {
            BallProgress(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp))
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // logo image
            Image(
                modifier = Modifier
                    .animateAlpha(
                        delayMillis = 500,
                        durationMillis = 1000
                    )
                    .width(200.dp)
                    .height(200.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(top = 32.dp))
            // no internet title
            if (viewModel.retryVisibility.value) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Internet connection error...",
                        style = AppTheme.typography.body,
                        color = AppTheme.colors.titleText
                    )
                }
            }
        }
        retryView(
            viewModel = viewModel,
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
        )
    }
}

@Composable
private fun retryView(
    viewModel: SplashViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    if (viewModel.retryVisibility.value) {
        GradientButton(
            modifier = modifier
                .padding(bottom = 16.dp),
            gradient = buttonEnabledGradient(),
            text = "Tap to try again...",
            textColor = Color.White
        ) {
            coroutineScope.launch {
                viewModel.retryVisibility.value = false
                checkUserStatus(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }
}

private suspend fun checkUserStatus(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    when {
        viewModel.checkUserIntroduction() == "null" -> {
            // navigate to introduction page
            navigateToPage(
                viewModel = viewModel,
                navController = navController,
                route = Screens.Introduction.route,
            )
        }
        viewModel.checkAuthentication() == "null" -> {
            // navigate to register page
            navigateToPage(
                viewModel = viewModel,
                navController = navController,
                route = Screens.Register.route,
            )
        }
        viewModel.checkCompleteRegister() == "null" -> {
            // navigate to Complete Register page
            navigateToPage(
                viewModel = viewModel,
                navController = navController,
                route = Screens.CompleteRegister.route,
            )
        }
        else -> {
            // navigate to main page
            navigateToPage(
                viewModel = viewModel,
                navController = navController,
                route = Screens.Main.route,
            )
        }
    }
}

private suspend fun navigateToPage(
    viewModel: SplashViewModel,
    navController: NavController,
    route: String,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        viewModel.retryVisibility.value = true
    }
}



