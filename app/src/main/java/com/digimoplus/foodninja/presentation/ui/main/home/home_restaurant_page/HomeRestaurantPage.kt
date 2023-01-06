@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.domain.util.LoadingSearchState
import com.digimoplus.foodninja.presentation.components.AnimatedTopAppBar
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

@Composable
fun HomeRestaurantPage(
    homeViewModel: HomeViewModel, // home viewModel for home header
    snackBarHostState: SnackbarHostState,
    navController: NavController,
) {

    val viewModel: HomeRestaurantViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val lazyGridState = rememberLazyGridState()
    val focusRequester = remember { FocusRequester() }

    viewModel.snackBarState = snackBarHostState
    viewModel.updateScrollPosition(lazyGridState.firstVisibleItemIndex)

    // focus on search textField
    LaunchedEffect(Unit) {
        if (homeViewModel.enableRestaurantFocus) {
            homeViewModel.enableRestaurantFocus = false
            this.coroutineContext.job.invokeOnCompletion {
                focusRequester.requestFocus()
            }
        }
    }

    // handle backPress
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
            when (viewModel.uiState) {
                // loading
                LoadingSearchState.Loading -> {
                    BallProgress()
                }
                // show restaurant list
                LoadingSearchState.NotLoading -> {
                    LazyVerticalGrid(
                        state = lazyGridState,
                        columns = GridCells.Fixed(2)
                    ) {
                        itemsIndexed(viewModel.restaurantsList) { index, item ->

                            // Restaurant to next page
                            viewModel.onChangeRestaurantsScrollPosition(index)
                            if (
                                (index + 1) >= viewModel.page * Constants.RESTAURANT_PAGE_SIZE &&
                                !viewModel.pageLoading
                            ) {
                                viewModel.onNextPage()
                            }

                            // Restaurant Card
                            RestaurantCardItem(
                                index = index,
                                model = item,
                                animationEnabled = homeViewModel.contentListAnim,
                                disableAnim = {
                                    homeViewModel.contentListAnim = false
                                }) {
                                navController.navigate(
                                    Screens.RestaurantDetail.createIdRoute(item.id)
                                )
                            }
                        }
                        item(
                            span = {
                                GridItemSpan(maxCurrentLineSpan)
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 100.dp, top = 16.dp)
                                    .align(
                                        Alignment.BottomCenter),
                            ) {
                                if (viewModel.pageLoading) {
                                    BallPulseSyncProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        color = AppTheme.colors.primary,
                                    )
                                }
                            }
                        }
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

