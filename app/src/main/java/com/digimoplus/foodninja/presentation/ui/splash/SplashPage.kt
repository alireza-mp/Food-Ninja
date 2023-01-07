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

    // check user introduction & authentication & isOnline
    LaunchedEffect(Unit) {
        checkUserIntroduction(
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
                checkUserIntroduction(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }
}

private suspend fun checkUserIntroduction(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    if (viewModel.checkUserIntroduction() == "null") {
        goToIntroductionPage(viewModel, navController)
    } else {
        checkUserAuthentication(viewModel, navController)
    }
}

private suspend fun checkUserAuthentication(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    if (viewModel.checkAuthentication() == "null") {
        goToSignUpPage(viewModel, navController)
    } else {
        checkUserInformation(viewModel, navController)
    }
}

private suspend fun checkUserInformation(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    if (viewModel.checkCompleteRegister() == "null") {
        goToUserInformationPage(viewModel, navController)
    } else {
        goToHomePage(viewModel, navController)
    }
}

suspend fun goToUserInformationPage(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(Screens.CompleteRegister.route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        viewModel.retryVisibility.value = true
    }
}

private suspend fun goToSignUpPage(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(Screens.Register.route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        viewModel.retryVisibility.value = true
    }
}

private suspend fun goToHomePage(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(Screens.Main.route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        viewModel.retryVisibility.value = true
    }
}

private suspend fun goToIntroductionPage(
    viewModel: SplashViewModel,
    navController: NavController,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(Screens.Introduction.route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        viewModel.retryVisibility.value = true
    }
}



