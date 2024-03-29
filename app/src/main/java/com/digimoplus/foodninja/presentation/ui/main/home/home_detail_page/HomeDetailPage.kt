package com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.HomePageState
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import com.digimoplus.foodninja.presentation.components.ListNoInternetItem
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.MainViewModel
import com.digimoplus.foodninja.presentation.ui.main.home.HomeHeader
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeDetailPage(
    navController: NavController,
    lazyListState: LazyListState,
) {

    val viewModel: HomeDetailViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()

    HandleErrors(viewModel = viewModel)

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            HomeHeader(listState = lazyListState)
        }

        item {
            HomeBody(
                viewModel = viewModel,
                homeViewModel = homeViewModel,
                listState = lazyListState,
                navController = navController,
            )
        }

        item {
            // update menu ui
            if (viewModel.uiState == UiState.NoInternet) {
                ListNoInternetItem(modifier = Modifier.height(300.dp)) {
                    viewModel.refresh()
                }
            }
        }

        itemsIndexed(items = viewModel.menuList, key = { index, _ -> index }) { index, item ->
            Box(
                modifier = Modifier.animateAlpha(
                    state = homeViewModel.launchAnimState,
                    delayMillis = 1000,
                    durationMillis = 1000,
                )
            ) {
                PopularMenuList(
                    navController = navController,
                    isLoading = viewModel.uiState == UiState.Loading,
                    index = index,
                    count = viewModel.menuList.size,
                    model = item,
                )
            }
        }
    }
}

@Composable
private fun HandleErrors(viewModel: HomeDetailViewModel) {
    val mainViewModel: MainViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        snapshotFlow { viewModel.handleErrorsState }.collect {
            if (it != DataState.Loading) {
                it.showSnackBarError(mainViewModel.snackBarState)
                viewModel.handleErrorsState = DataState.Loading
            }
        }
    }
}


@Composable
private fun HomeBody(
    homeViewModel: HomeViewModel,
    viewModel: HomeDetailViewModel,
    listState: LazyListState,
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {

            Spacer(modifier = Modifier.padding(top = 21.dp))

            Image(
                painter = painterResource(id = R.drawable.home_baner),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .animateAlpha(
                        state = homeViewModel.launchAnimState,
                        delayMillis = 500,
                        durationMillis = 1000,
                    ),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.padding(top = 25.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .animateAlpha(
                        state = homeViewModel.launchAnimState,
                        delayMillis = 800,
                        durationMillis = 1000,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Nearest Restaurant",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                )
                TextButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                        homeViewModel.pageState.value = HomePageState.RestaurantPage
                    }
                }) {
                    Text(
                        text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .animateAlpha(
                        state = homeViewModel.launchAnimState,
                        delayMillis = 800,
                        durationMillis = 1000,
                    ),
            ) {
                // restaurant list
                NearestRestaurantList(viewModel = viewModel, navController = navController)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .animateAlpha(
                        state = homeViewModel.launchAnimState,
                        delayMillis = 1000,
                        durationMillis = 1000,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Popular Menu",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                )
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                            homeViewModel.pageState.value = HomePageState.MenuPage
                        }
                    },
                ) {
                    Text(
                        text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
        }
    }
}
