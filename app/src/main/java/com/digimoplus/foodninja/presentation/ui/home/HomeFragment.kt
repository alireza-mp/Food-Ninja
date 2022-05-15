@file:OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
package com.digimoplus.foodninja.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.base_dispalys.DisplayBackgroundImage
import com.digimoplus.foodninja.presentation.components.base_dispalys.HomeDisplay
import com.digimoplus.foodninja.presentation.components.util.bottomNavigationTabValues
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import com.digimoplus.foodninja.presentation.ui.home.chat.ChatPage
import com.digimoplus.foodninja.presentation.ui.home.profile.ProfilePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import ir.digimoplus.bottom_navigation.CustomBottomNavigation

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    HomePage(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
private fun HomePage(viewModel: HomeViewModel) {

    val pagerState = rememberPagerState(pageCount = 3)

    DisplayBackgroundImage(
        lightBackground = R.drawable.display_background_light,
        darkBackground = R.drawable.display_background_dark,
        paddingValues = PaddingValues(
            start = AppTheme.dimensions.grid_2,
            end = AppTheme.dimensions.grid_2
        )
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
                    0 -> HomeDisplay(viewModel = viewModel)
                    1 -> ProfilePage()
                    2 -> ChatPage()
                }
            }

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
                    )
                )
            }
        }
    }
}
