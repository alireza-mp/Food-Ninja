package com.digimoplus.foodninja.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashPage(navController: NavController) {

    val viewModel: SplashViewModel = hiltViewModel()
    val splashLaunchAnim = remember { mutableStateOf(0f) }
    val retryVisibility = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // check user introduction & authentication & isOnline
    LaunchedEffect(Unit) {
        checkUserIntroduction(
            viewModel = viewModel,
            navController = navController,
            retryVisibility = retryVisibility
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .animateAlpha(
                    state = splashLaunchAnim,
                    durationMillis = 500),
            painter = painterResource(id = if (AppTheme.colors.isLight) R.drawable.background_light else R.drawable.background_dark),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        if (!retryVisibility.value) {
            BallProgress(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .animateAlpha(
                        state = splashLaunchAnim,
                        delayMillis = 500, durationMillis = 1000)
                    .width(200.dp)
                    .height(200.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(top = 32.dp))
            if (retryVisibility.value) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Internet connection error...",
                        style = AppTheme.typography.body,
                        color = AppTheme.colors.titleText
                    )
                }
            }
        }
        if (retryVisibility.value) {
            GradientButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                gradient = buttonEnabledGradient(),
                text = "Tap to try again...",
                textColor = Color.White
            ) {
                coroutineScope.launch {
                    retryVisibility.value = false
                    checkUserIntroduction(
                        viewModel = viewModel,
                        navController = navController,
                        retryVisibility = retryVisibility
                    )
                }
            }
        }
    }
    //start launch anim
    LaunchedEffect(Unit) {
        splashLaunchAnim.value = 1f
    }
}

private suspend fun checkUserIntroduction(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController,
) {
    viewModel.checkIntroduction.collect { state ->
        if (state == "null") {
            goToIntroductionPage(retryVisibility, viewModel, navController)
        } else {
            checkUserAuthentication(retryVisibility, viewModel, navController)
        }
    }
}

private suspend fun checkUserAuthentication(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController,
) {
    viewModel.checkAuthentication.collect { state ->
        if (state == "null") {
            goToSignUpPage(retryVisibility, viewModel, navController)
        } else {
            checkUserInformation(retryVisibility, viewModel, navController)
        }
    }
}

private suspend fun checkUserInformation(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController,
) {
    viewModel.checkUserInformation.collect { state ->
        if (state == "null") {
            goToUserInformationPage(retryVisibility, viewModel, navController)
        } else {
            goToHomePage(retryVisibility, viewModel, navController)
        }
    }

}

suspend fun goToUserInformationPage(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(Screens.UserInformation.route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        retryVisibility.value = true
    }
}

private suspend fun goToSignUpPage(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController,
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(Screens.SignUp.route) {
            // remove splash page from backstack
            popUpTo(Screens.Splash.route) {
                inclusive = true
            }
        }
    } else {
        retryVisibility.value = true
    }
}

private suspend fun goToHomePage(
    retryVisibility: MutableState<Boolean>,
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
        retryVisibility.value = true
    }
}

private suspend fun goToIntroductionPage(
    retryVisibility: MutableState<Boolean>,
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
        retryVisibility.value = true
    }
}



