@file:OptIn(ExperimentalPagerApi::class)

package com.digimoplus.foodninja.presentation.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.digimoplus.foodninja.presentation.components.util.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import com.digimoplus.foodninja.presentation.ui.chat_detail.ChatDetailPage
import com.digimoplus.foodninja.presentation.ui.main.MainPage
import com.digimoplus.foodninja.presentation.ui.main.basket.BasketPage
import com.digimoplus.foodninja.presentation.ui.menu_detail.MenuDetailPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.introduction.IntroductionPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.login_process.forget_password.ForgetPasswordPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.login_process.login.LoginPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.CompleteRegisterPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.USER_INFO_FAMILY
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.USER_INFO_NAME
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio.USER_INFO_PHONE
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.location.ChooseLocationPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.payment.PaymentPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.register.RegisterPage
import com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.upload_profile.UploadProfilePage
import com.digimoplus.foodninja.presentation.ui.on_boarding.success.SuccessPage
import com.digimoplus.foodninja.presentation.ui.restaurant_detail.RestaurantDetailPage
import com.digimoplus.foodninja.presentation.ui.splash.SplashPage
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


var appHeight = 0f
var appWidth = 0f

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

                // get display width px
                val width = with(LocalDensity.current) { maxWidth.toPx() }
                // 1/2000 % of width
                appWidth = (width * 0.002f)

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
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
    ) {
        composable(
            route = Screens.Splash.route,/*
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
            }*/
        ) {
            SplashPage(
                navController = navController,
            )
        }
        composable(
            route = Screens.Introduction.route,/*
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
            }*/
        ) {
            IntroductionPage(
                navController = navController
            )
        }
        composable(
            route = Screens.Login.route,/*
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
            }*/
        ) {
            LoginPage(
                navController = navController
            )
        }
        composable(
            route = Screens.Register.route,/*
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
            }*/
        ) {
            RegisterPage(
                navController = navController
            )
        }
        composable(
            route = Screens.Main.route,/*
            enterTransition = {
                when (initialState.destination.route) {
                    Screens.BasketPage.route -> null
                    else -> screenEnterTransition()
                }
            },
            exitTransition = {
                when (initialState.destination.route) {
                    Screens.BasketPage.route -> null
                    else -> screenExitTransition()
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Screens.BasketPage.route -> null
                    else -> screenPopEnterTransition()
                }
            },
            popExitTransition = {
                when (initialState.destination.route) {
                    Screens.BasketPage.route -> null
                    else -> screenPopExitTransition()
                }
            }*/
        ) {
            MainPage(
                navController = navController
            )
        }
        composable(
            route = Screens.CompleteRegister.route,/*

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
            },*/
            arguments = listOf(navArgument("name") {
                defaultValue = ""
            })) { backStackEntry ->
            CompleteRegisterPage(
                navController = navController,
                name = backStackEntry.arguments?.getString("name") ?: ""
            )
        }
        composable(
            route = Screens.ForgetPassword.route,/*
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
            }*/
        ) {
            ForgetPasswordPage()
        }
        composable(
            route = Screens.Payment.route,/*
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
            }*/
        ) {
            PaymentPage(
                navController = navController,
                name = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_NAME),
                family = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_FAMILY),
                phone = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_PHONE),
            )
        }
        composable(
            route = Screens.UploadProfile.route,/*
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
            }*/
        ) {
            UploadProfilePage(
                navController = navController,
                name = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_NAME),
                family = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_FAMILY),
                phone = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_PHONE),
            )
        }
        composable(
            route = Screens.ChooseLocation.route,/*
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
            }*/
        ) {
            ChooseLocationPage(
                navController = navController,
                name = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_NAME),
                family = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_FAMILY),
                phone = navController.previousBackStackEntry?.arguments?.getString(USER_INFO_PHONE),
            )
        }
        composable(
            route = Screens.Success.route,/*
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
            }*/
        ) {
            SuccessPage(
                navController = navController,
            )
        }
        composable(
            route = Screens.RestaurantDetail.route,
            arguments = listOf(navArgument(name = "id") { defaultValue = 1 }),/*
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
            }*/
        ) { backStackEntry ->
            RestaurantDetailPage(
                navController = navController,
                restaurantId = backStackEntry.arguments?.getInt("id") ?: 1
            )
        }
        composable(
            route = Screens.MenuDetail.route,
            arguments = listOf(navArgument(name = "id") { defaultValue = 1 }),/*
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
            }*/
        ) { backStackEntry ->
            MenuDetailPage(
                navController = navController,
                menuId = backStackEntry.arguments?.getInt("id") ?: 1
            )
        }
        composable(
            route = Screens.Basket.route,/*
            enterTransition = {
                screenSlideInVerticalTransition()
            },
            exitTransition = {
                null
            },
            popEnterTransition = {
                null
            },
            popExitTransition = {
                screenSlideOutVerticalTransition()
            }*/
        ) {
            BasketPage(navController = navController)
        }
        composable(
            route = Screens.ChatDetail.route,/*
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
            }*/
        ) {
            ChatDetailPage(navController = navController)
        }
    }
}