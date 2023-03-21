@file:OptIn(ExperimentalFoundationApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun IntroductionPage(
    navController: NavController,
) {
    val viewModel: IntroductionViewModel = hiltViewModel()
    val pagerState = rememberPagerState()

    HorizontalPager(
        state = pagerState,
        pageCount = 2,
        pageSpacing = 20.dp,
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