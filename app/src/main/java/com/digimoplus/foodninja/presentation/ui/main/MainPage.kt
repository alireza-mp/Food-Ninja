@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalFoundationApi::class
)

package com.digimoplus.foodninja.presentation.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.bottomNavigationTabValues
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.chat.ChatPage
import com.digimoplus.foodninja.presentation.ui.main.home.HomePage
import com.digimoplus.foodninja.presentation.ui.main.profile.ProfilePage
import ir.digimoplus.bottom_navigation.CustomBottomNavigation

@Composable
fun MainPage(navController: NavController) {

    val viewModel: MainViewModel = hiltViewModel()
    val pagerState = rememberPagerState()



    PageMainBackgroundImage(
        lightBackground = R.drawable.main_page_background_light,
        darkBackground = R.drawable.main_page_background_dark,
        paddingValues = PaddingValues(
            start = 0.dp,
            end = 0.dp
        ),
        snackBarState = viewModel.snackBarState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            HorizontalPager(
                state = pagerState,
                pageCount = 3,
                pageSpacing = 20.dp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxSize(),
            ) { index ->
                when (index) {
                    0 -> Home(navController, viewModel)
                    1 -> ProfilePage()
                    2 -> ChatPage(navController = navController)
                }
            }

            BottomNavigation(
                modifier = Modifier.align(Alignment.BottomCenter),
                viewModel = viewModel,
                pagerState = pagerState,
                navController = navController,
            )

        }
    }
}

@Composable
private fun Home(
    navController: NavController,
    viewModel: MainViewModel,
) {
    HomePage(
        showBottomTab = { visibility ->
            viewModel.showBottomTab.value = visibility
        },
        navController = navController
    )
}

@Composable
private fun BottomNavigation(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    pagerState: PagerState,
    navController: NavController,
) {
    // update basket badge
    LaunchedEffect(Unit) {
        viewModel.updateBasketBadge()
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = viewModel.showBottomTab.value,
    ) {
        Card(
            backgroundColor = AppTheme.colors.surface,
            modifier = modifier
                .fillMaxWidth()
                .height(74.dp)
                .padding(bottom = 11.dp, end = 20.dp, start = 20.dp)
                .coloredShadow(
                    offsetX = 2.dp,
                    offsetY = 2.dp,
                    alpha = 0.09f,
                ),
            shape = RoundedCornerShape(22.dp),
            elevation = 0.dp,
        ) {
            CustomBottomNavigation(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 10.dp),
                pagerState = pagerState,
                tabValues = bottomNavigationTabValues(
                    basketBadge = viewModel.basketBadge,
                    chatBadge = viewModel.chatBadge
                ),
                onTabClickListener = { position ->
                    // basket clicked
                    if (position == 2) {
                        navController.navigate(Screens.Basket.route)
                    }
                }
            )
        }
    }
}




