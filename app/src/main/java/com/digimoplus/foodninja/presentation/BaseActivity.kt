@file:OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)

package com.digimoplus.foodninja.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.navArgument
import com.digimoplus.foodninja.domain.model.UserInfo
import com.digimoplus.foodninja.presentation.components.util.screenEnterTransition
import com.digimoplus.foodninja.presentation.components.util.screenExitTransition
import com.digimoplus.foodninja.presentation.components.util.screenPopEnterTransition
import com.digimoplus.foodninja.presentation.components.util.screenPopExitTransition
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import com.digimoplus.foodninja.presentation.ui.main.MainPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.introduction.IntroductionPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_in_process.forget_password.ForgetPasswordPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_in_process.sign_in.SingInPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.UserInformationPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.location.ChooseLocationPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.payment.PaymentPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.sign_up.SignUpPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.upload_profile.UploadProfilePage
import com.digimoplus.foodninja.presentation.ui.on_boarding.success_notification.SuccessPage
import com.digimoplus.foodninja.presentation.ui.splash.SplashPage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(isDark(isSystemInDarkTheme())) {
                // start nav host
                FoodNinja()
                // remove white display bug
                LaunchedEffect(Unit) {
                    delay(50)
                    window.setBackgroundDrawableResource(android.R.color.transparent)
                }
            }
        }
    }
}

@Composable
fun FoodNinja() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.SuccessPage.route,
    ) {
        composable(route = Screens.Splash.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            SplashPage(
                navController = navController,
            )
        }
        composable(route = Screens.Introduction.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            IntroductionPage(
                navController = navController
            )
        }
        composable(route = Screens.SignIn.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            SingInPage(
                navController = navController
            )
        }
        composable(route = Screens.SignUp.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            SignUpPage(
                navController = navController
            )
        }
        composable(route = Screens.Main.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            MainPage(
                navController = navController
            )
        }
        composable(
            route = Screens.UserInformation.route,

            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            },
            arguments = listOf(navArgument("name") {
                defaultValue = ""
            })) { backStackEntry ->
            UserInformationPage(
                navController = navController,
                name = backStackEntry.arguments?.getString("name") ?: ""
            )
        }
        composable(route = Screens.ForgetPassword.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            ForgetPasswordPage()
        }
        composable(route = Screens.Payment.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            PaymentPage(
                navController = navController,
                userInfo = navController.previousBackStackEntry?.arguments?.getParcelable<UserInfo>(
                    "user")
            )
        }
        composable(route = Screens.UploadProfile.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            UploadProfilePage(
                navController = navController,
                userInfo = navController.previousBackStackEntry?.arguments?.getParcelable<UserInfo>(
                    "user")
            )
        }
        composable(route = Screens.ChooseLocation.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            ChooseLocationPage(
                navController = navController,
                userInfo = navController.previousBackStackEntry?.arguments?.getParcelable<UserInfo>(
                    "user")
            )
        }
        composable(route = Screens.SuccessPage.route,
            enterTransition = {
                screenEnterTransition()
            },
            exitTransition = {
                screenExitTransition()
            },
            popEnterTransition = {
                screenPopEnterTransition()
            },
            popExitTransition = {
                screenPopExitTransition()
            }) {
            SuccessPage(
                navController = navController,
            )
        }
    }
}