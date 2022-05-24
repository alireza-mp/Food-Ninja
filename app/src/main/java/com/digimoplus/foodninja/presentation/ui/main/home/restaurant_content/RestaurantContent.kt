@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.*
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.AnimatedTopAppBar
import com.digimoplus.foodninja.presentation.components.CircleBallProgress
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.digimoplus.foodninja.presentation.util.LoadingSearchState
import com.digimoplus.foodninja.presentation.util.restaurantListPaddingBottom
import com.digimoplus.foodninja.repository.PAGE_SIZE
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

@Composable
fun RestaurantContent(
    homeViewModel: HomeViewModel, // home viewModel for home header
    snackBarHostState: SnackbarHostState,
) {

    val viewModel: HomeRestaurantViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val focusRequester = remember { FocusRequester() }

    viewModel.snackBarState = snackBarHostState
    viewModel.updateScrollPosition(lazyGridState.firstVisibleItemIndex)

    // focus on search textField
    LaunchedEffect(Unit) {
        if (homeViewModel.enabledSearch) {
            homeViewModel.enabledSearch = false
            this.coroutineContext.job.invokeOnCompletion {
                focusRequester.requestFocus()
            }
        }
    }

    // handle backPress
    val focusManager = LocalFocusManager.current
    BackHandler(enabled = viewModel.backState.value) {
        focusManager.clearFocus()
        homeViewModel.search.value = ""
        if (viewModel.searchIng) {
            coroutineScope.launch(Dispatchers.Main) {
                viewModel.resetList()
                viewModel.searchIng = false
            }
        }
        viewModel.backState.value = false
    }

    // top app bar
    AnimatedTopAppBar(
        viewModel = viewModel,
        homeViewModel = homeViewModel,
        focusRequester = focusRequester
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            when (viewModel.loadingRestaurant.value) {
                // loading
                LoadingSearchState.Loading -> {
                    CircleBallProgress()
                }
                // show restaurant list
                LoadingSearchState.NotLoading -> {
                    LazyVerticalGrid(
                        state = lazyGridState,
                        columns = GridCells.Fixed(2)
                    ) {
                        itemsIndexed(viewModel.restaurantAllList) { index, item ->

                            // Restaurant to next page
                            viewModel.onChangeRestaurantsScrollPosition(index)
                            if ((index + 1) >= viewModel.page.value * PAGE_SIZE && !viewModel.pageLoading.value) {
                                viewModel.onNextPage()
                            }

                            // Restaurant Card
                            RestaurantCardItem(
                                index = index,
                                model = item,
                                padding = PaddingValues(
                                    start = AppTheme.dimensions.grid_2,
                                    end = AppTheme.dimensions.grid_2,
                                    top = AppTheme.dimensions.grid_1_5,
                                    bottom = restaurantListPaddingBottom(index = index,
                                        viewModel = viewModel)
                                ),
                                animationEnabled = viewModel.listAnim,
                                disableAnim = {
                                    viewModel.listAnim = false
                                })
                        }
                    }
                    if (viewModel.pageLoading.value) {
                        BallPulseSyncProgressIndicator(
                            modifier = Modifier
                                .padding(bottom = 100.dp)
                                .align(
                                    Alignment.BottomCenter),
                            color = AppTheme.colors.primary,
                        )
                    }
                }

                // show lottie animation not found
                LoadingSearchState.NotFound -> {
                    Box(modifier = Modifier.padding(bottom = 100.dp)) {
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
                            R.raw.not_found))
                        LottieAnimation(
                            composition = composition,
                            iterations = LottieConstants.IterateForever,
                        )

                        Text(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            text = "Not Found",
                            style = AppTheme.typography.body,
                            color = AppTheme.colors.onTitleText
                        )
                    }
                }
            }
        }
    }
}

