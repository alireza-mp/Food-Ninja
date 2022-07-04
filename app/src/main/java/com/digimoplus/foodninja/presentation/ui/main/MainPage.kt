@file:OptIn(ExperimentalPagerApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class)

package com.digimoplus.foodninja.presentation.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.bottomNavigationTabValues
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.chat.ChatPage
import com.digimoplus.foodninja.presentation.ui.main.home.HomePage
import com.digimoplus.foodninja.presentation.ui.main.profile.ProfilePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ir.digimoplus.bottom_navigation.CustomBottomNavigation

@Composable
fun MainPage(navController: NavController) {

    val viewModel: MainViewModel = hiltViewModel()
    val pagerState = rememberPagerState(pageCount = 3)

    PageMainBackgroundImage(
        lightBackground = R.drawable.main_page_background_light,
        darkBackground = R.drawable.main_page_background_dark,
        paddingValues = PaddingValues(
            start = AppTheme.dimensions.grid_2,
            end = AppTheme.dimensions.grid_2
        ),
        snackBarState = viewModel.snackBarState
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
        ) {

            HorizontalPager(
                state = pagerState,
                itemSpacing = 20.dp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxSize()
            ) { index ->
                when (index) {
                    0 -> HomePage(
                        snackBarHostState = viewModel.snackBarState,
                        showBottomTab = { visibility ->
                            viewModel.showBottomTab.value = visibility
                        },
                        navController = navController
                    )
                    1 -> ProfilePage()
                    2 -> ChatPage()
                }
            }
            AnimatedVisibility(modifier = Modifier.align(Alignment.BottomCenter),
                visible = viewModel.showBottomTab.value) {
                Card(
                    backgroundColor = AppTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(bottom = AppTheme.dimensions.grid_1)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 4.dp,
                ) {
                    CustomBottomNavigation(
                        modifier = Modifier,
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        pagerState = pagerState,
                        tabValues = bottomNavigationTabValues(
                            basketBadge = viewModel.basketBadge.value,
                            chatBadge = viewModel.chatBadge.value
                        ),
                        onTabClickListener = {
                            if (it == 2) { // if basket clicked
                                TODO("go to basket page")
                            }
                        }
                    )
                }
            }

        }
    }
}




