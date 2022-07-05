package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun IntroductionPage(
    navController: NavController,
) {
    val viewModel: IntroductionViewModel = hiltViewModel()
    val pagerState = rememberPagerState(pageCount = 2)

    HorizontalPager(
        state = pagerState,
        itemSpacing = 20.dp,
        modifier = Modifier.background(color = AppTheme.colors.background)
    ) { index ->
        when (index) {
            0 -> IntroductionPageOne(pagerState = pagerState)
            1 -> IntroductionPageTwo(
                viewModel = viewModel,
                navController = navController,
            )
        }
    }
}