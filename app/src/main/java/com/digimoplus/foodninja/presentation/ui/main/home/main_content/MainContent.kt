package com.digimoplus.foodninja.presentation.ui.main.home.main_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.HomePageState
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeHeader
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun MainContent(
    homeViewModel: HomeViewModel,
    snackBarHostState: SnackbarHostState,
    navController: NavController,
) {

    val viewModel: HomeMainViewModel = hiltViewModel()
    viewModel.snackBarState = snackBarHostState
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            HomeHeader(viewModel = homeViewModel, listState = lazyListState)
        }

        item {
            HomeBody(viewModel = viewModel,
                homeViewModel = homeViewModel,
                listState = lazyListState,
                pageState = homeViewModel.pageState,
                navController = navController
            )
        }

        // update menu ui
        if (viewModel.loadingMenu.value == UiState.NoInternet) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    TextButton(
                        onClick = {
                            viewModel.callRequests()
                        }
                    ) {
                        Text(
                            text = "Failed! Tap to try Again",
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.body1
                        )
                    }
                }
            }
        } else {
            items(count = 6) { index ->
                MenuListItem(
                    navController = navController,
                    state = viewModel.loadingMenu.value,
                    launchAnimState = homeViewModel.launchAnimState,
                    index = index,
                    count = 6,
                    list = viewModel.menuList
                )
            }
        }

    }
}


@Composable
private fun HomeBody(
    homeViewModel: HomeViewModel,
    viewModel: HomeMainViewModel,
    listState: LazyListState,
    navController: NavController,
    pageState: MutableState<HomePageState>,
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column {

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Image(
                painter = painterResource(id = R.drawable.home_baner),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .animateAlpha(state = homeViewModel.launchAnimState,
                        delayMillis = 500,
                        durationMillis = 1000),
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .animateAlpha(state = homeViewModel.launchAnimState,
                    delayMillis = 800,
                    durationMillis = 1000),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Nearest Restaurant",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                )
                TextButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                        pageState.value = HomePageState.RestaurantContent
                    }
                }) {
                    Text(text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1)
                }
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .animateAlpha(state = homeViewModel.launchAnimState,
                    delayMillis = 800,
                    durationMillis = 1000)) {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    NearestRestaurantList(viewModel = viewModel, navController = navController)
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .animateAlpha(state = homeViewModel.launchAnimState,
                    delayMillis = 1000,
                    durationMillis = 1000),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Popular Menu",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                )
                TextButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                        pageState.value = HomePageState.MenuContent
                    }
                }) {
                    Text(text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1)
                }
            }
        }
    }
}
