package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import android.os.Bundle
import android.util.Log
import android.view.ContentInfo
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.navigation.findNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.digimoplus.foodninja.R
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.theme.AppDimensions
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.getDimensions
import com.digimoplus.foodninja.presentation.theme.isDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
class IntroductionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {

                    val dimensions = getDimensions(LocalConfiguration.current.screenHeightDp)

                    IntroductionPage(findNavController(),dimensions)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun IntroductionPage(navController: NavController,dimensions: AppDimensions) {

    val pagerState = rememberPagerState(pageCount = 2)

    HorizontalPager(
        state = pagerState,
        itemSpacing = 20.dp,
        modifier = Modifier.background(color = AppTheme.colors.background)
    ) { index ->
        when (index) {
            0 -> IntroductionPageOne(pagerState = pagerState, dimensions = dimensions)
            1 -> IntroductionPageTwo(navController = navController, dimensions = dimensions)
        }
    }
}