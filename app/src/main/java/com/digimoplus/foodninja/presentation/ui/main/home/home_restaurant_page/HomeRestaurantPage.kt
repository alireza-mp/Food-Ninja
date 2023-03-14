@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)

package com.digimoplus.foodninja.presentation.ui.main.home.home_restaurant_page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.LoadingSearchState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.MainViewModel
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import navigateWithSaveState

@Composable
fun HomeRestaurantPage(
    navController: NavController,
) {
    val viewModel: HomeRestaurantViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val lazyGridState = rememberLazyGridState()
    val focusRequester = remember { FocusRequester() }


    changeScrollingListener(viewModel, lazyGridState)

    // focus on search textField
    LaunchedEffect(Unit) {
        if (homeViewModel.enableRestaurantFocus) {
            homeViewModel.enableRestaurantFocus = false
            this.coroutineContext.job.invokeOnCompletion {
                focusRequester.requestFocus()
            }
        }
    }

    HandleErrors(viewModel = viewModel)

    // handle backPress
    HandleBackPress(viewModel = viewModel, homeViewModel = homeViewModel)

    // top app bar
    AnimatedTopAppBar(
        viewModel = viewModel, focusRequester = focusRequester
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            when (viewModel.uiState) {
                // loading
                LoadingSearchState.Loading -> {
                    BallProgress()
                }
                // show restaurant list
                LoadingSearchState.NotLoading -> {
                    RestaurantsContent(
                        modifier = Modifier.align(Alignment.BottomCenter), // grid progress modifier
                        homeViewModel = homeViewModel,
                        viewModel = viewModel,
                        lazyGridState = lazyGridState,
                        navController = navController,
                    )
                }

                // show lottie animation not found
                LoadingSearchState.NotFound -> {
                    NotFoundContent()
                }

                LoadingSearchState.NotInternet -> {
                    NoInternetContent {
                        viewModel.refresh()
                    }
                }
            }
        }
    }
}

private fun changeScrollingListener(
    viewModel: HomeRestaurantViewModel,
    lazyGridState: LazyGridState,
) {
    viewModel.updateScrollPosition(lazyGridState.firstVisibleItemIndex)
}

@Composable
private fun HandleErrors(viewModel: HomeRestaurantViewModel) {
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
private fun HandleBackPress(viewModel: HomeRestaurantViewModel, homeViewModel: HomeViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    BackHandler(enabled = viewModel.backState) {
        focusManager.clearFocus()
        homeViewModel.search.value = ""
        if (viewModel.isSearchIng) {
            coroutineScope.launch(Dispatchers.Main) {
                viewModel.disableSearch()
            }
        }
        viewModel.backState = false
    }
}

@Composable
private fun RestaurantsContent(
    homeViewModel: HomeViewModel,
    viewModel: HomeRestaurantViewModel,
    lazyGridState: LazyGridState,
    navController: NavController,
    modifier: Modifier = Modifier, // grid progress modifier
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            top = if (viewModel.scrollUp.value == true) 12.dp else 20.dp,
        ),
        state = lazyGridState,
        columns = GridCells.Fixed(2),
    ) {
        itemsIndexed(viewModel.restaurantsList) { index, item ->

            // Restaurant to next page
            viewModel.onChangeRestaurantsScrollPosition(index)
            if ((index + 1) >= viewModel.page * Constants.RESTAURANT_PAGE_SIZE && !viewModel.pageLoading) {
                viewModel.onNextPage()
            }

            // Restaurant Card
            RestaurantCardItem(
                index = index,
                model = item,
                animationEnabled = homeViewModel.contentListAnim,
                disableAnim = {
                    homeViewModel.contentListAnim = false
                },
                paddingValues = PaddingValues(
                    top = 12.dp,
                    bottom = 0.dp,
                    start = 14.dp,
                    end = 14.dp,
                )
            ) {
                navController.navigateWithSaveState(
                    Screens.RestaurantDetail.createIdRoute(item.id)
                )
            }
        }
        item(span = {
            GridItemSpan(maxCurrentLineSpan)
        }) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (viewModel.isLastPage()) 100.dp else 150.dp, top = 16.dp
                    ),
            ) {
                if (viewModel.pageLoading) {
                    BallPulseSyncProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = AppTheme.colors.primary,
                    )
                }
            }
        }
    }
}

