package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.HomePageState
import com.digimoplus.foodninja.presentation.components.SearchAppBar
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page.HomeDetailPage
import com.digimoplus.foodninja.presentation.ui.main.home.home_filter_page.HomeSearchFilterPage
import com.digimoplus.foodninja.presentation.ui.main.home.home_menu_page.HomeMenuPage
import com.digimoplus.foodninja.presentation.ui.main.home.home_restaurant_page.HomeRestaurantPage
import kotlinx.coroutines.delay

@Composable
fun HomePage(
    showBottomTab: (visibility: Boolean) -> Unit,
    navController: NavController,
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val lazyListState = rememberLazyListState()

    HandleBackPress(homeViewModel = homeViewModel, showBottomTab = showBottomTab)

    HomePages(
        homeViewModel = homeViewModel,
        navController = navController,
        lazyListState = lazyListState,
        showBottomTab = showBottomTab,
    )

    LaunchedEffect(Unit) {
        delay(500)
        homeViewModel.launchAnimState.value = 1f
    }

}

@Composable
private fun HomePages(
    homeViewModel: HomeViewModel,
    navController: NavController,
    lazyListState: LazyListState,
    showBottomTab: (visibility: Boolean) -> Unit,
) {
    // update display state
    when (homeViewModel.pageState.value) {
        HomePageState.DetailPage -> {
            homeViewModel.backHandler.value = false
            HomeDetailPage(
                navController = navController, lazyListState = lazyListState
            )
        }

        HomePageState.RestaurantPage -> {
            homeViewModel.backHandler.value = true
            HomeRestaurantPage(
                navController = navController
            )
        }

        HomePageState.MenuPage -> {
            homeViewModel.backHandler.value = true
            HomeMenuPage(
                navController = navController
            )
        }

        HomePageState.SearchFilterPage -> {
            homeViewModel.backHandler.value = true
            showBottomTab(false)
            HomeSearchFilterPage(showBottomTab = showBottomTab)
        }
    }
}

@Composable
private fun HandleBackPress(
    homeViewModel: HomeViewModel,
    showBottomTab: (visibility: Boolean) -> Unit,
) {
    BackHandler(enabled = homeViewModel.backHandler.value) {
        homeViewModel.contentListAnim = true
        when (homeViewModel.pageState.value) {
            HomePageState.RestaurantPage -> {
                homeViewModel.pageState.value = HomePageState.DetailPage
            }
            HomePageState.MenuPage -> {
                homeViewModel.pageState.value = HomePageState.DetailPage
            }
            HomePageState.SearchFilterPage -> {
                homeViewModel.pageState.value = HomePageState.DetailPage
                showBottomTab(true)
            }
            else -> {
                // :)
            }
        }
    }
}

// homeViewModel
// Header used into all pages home display
@Composable
fun HomeHeader(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    searchTopPadding: Dp = 24.dp,
    listState: LazyListState? = null,
    searchQuery: (query: String) -> Unit = {},
    focusRequester: FocusRequester? = null,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.padding(top = 40.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .animateAlpha(state = viewModel.launchAnimState, durationMillis = 1000)) {

            Text(text = "Find Your Favorite Food",
                lineHeight = 40.sp,
                color = AppTheme.colors.titleText,
                modifier = Modifier.width(250.dp),
                style = AppTheme.typography.h4)

            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center) {
                Button(onClick = {

                },
                    contentPadding = PaddingValues(horizontal = 16.dp,
                        vertical = 14.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                    modifier = Modifier
                        .defaultMinSize(1.dp, 1.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.surface),
                    shape = RoundedCornerShape(20.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_notifiaction),
                        contentDescription = "",
                        tint = AppTheme.colors.primary
                    )
                }
            }

        }

        Spacer(modifier = Modifier.padding(top = searchTopPadding))

        SearchAppBar(
            viewModel = viewModel,
            coroutineScope = coroutineScope,
            listState = listState,
            focusRequester = focusRequester,
            searchQuery = {
                searchQuery(it)
            }
        )

    }
}
