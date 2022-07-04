@file:OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)

package com.digimoplus.foodninja.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.navArgument
import com.digimoplus.foodninja.presentation.ui.restaurant_detail.RestaurantDetailPage
import com.digimoplus.foodninja.domain.model.UserInfo
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
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


var appHeight = 0f

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                // get display height px
                val height = with(LocalDensity.current) { maxHeight.toPx() }
                // 1/1000 % of height
                appHeight = (height * 0.001f)
            }
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
        startDestination = Screens.Splash.route,
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
        composable(
            route = Screens.RestaurantDetail.route,
            arguments = listOf(navArgument(name = "id") { defaultValue = 1 }),
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
            }
        ) { backStackEntry ->
            RestaurantDetailPage(
                navController = navController,
                restaurantId = 2/*backStackEntry.arguments?.getInt("id") ?: 1*/
            )
        }
    }
}