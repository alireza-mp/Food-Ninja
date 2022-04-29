package com.digimoplus.foodninja.presentation.ui.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.components.util.buttonGradient
import com.digimoplus.foodninja.presentation.theme.isDark
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {

                    SplashPage(
                        navController = findNavController(),
                        viewModel = viewModel,
                        requireContext()
                    )

                }
            }
        }
    }
}

@Composable
fun SplashPage(navController: NavController, viewModel: SplashViewModel, context: Context) {

    val retryVisibility = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = if (AppTheme.colors.isLight) R.drawable.background_light else R.drawable.background_dark),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_6))
            if (!retryVisibility.value) {
                CircularProgressIndicator(color = AppTheme.colors.primary)
            } else {
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
                    .align(Alignment.BottomCenter).padding(bottom = AppTheme.dimensions.grid_2),
                gradient = buttonGradient(),
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
}

private suspend fun checkUserIntroduction(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController
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
    navController: NavController
) {
    viewModel.checkAuthentication.collect { state ->
        if (state == "null") {
            goToSignUpPage(retryVisibility, viewModel, navController)
        } else {
            goToHomePage(retryVisibility, viewModel, navController)
        }
    }
}

private suspend fun goToSignUpPage(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(R.id.action_splashFragment_to_signUpFragment)
    } else {
        retryVisibility.value = true
    }
}

private suspend fun goToHomePage(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(R.id.action_splashFragment_to_homeFragment)
    } else {
        retryVisibility.value = true
    }
}

private suspend fun goToIntroductionPage(
    retryVisibility: MutableState<Boolean>,
    viewModel: SplashViewModel,
    navController: NavController
) {
    delay(1500)
    if (viewModel.isOnline()) {
        navController.navigate(R.id.action_splashFragment_to_introductionFragment)
    } else {
        retryVisibility.value = true
    }
}


