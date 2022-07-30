package com.digimoplus.foodninja.presentation.ui.main.home.menu_content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.AnimatedTopAppBar
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.digimoplus.foodninja.presentation.util.LoadingSearchState
import com.digimoplus.foodninja.repository.PAGE_SIZE
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MenuContent(
    homeViewModel: HomeViewModel,
    snackBarHostState: SnackbarHostState,
    navController: NavController,
) {

    val viewModel: HomeMenuViewModel = hiltViewModel()
    viewModel.snackBarHostState = snackBarHostState
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    viewModel.updateScrollPosition(lazyListState.firstVisibleItemIndex
    )
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
        homeViewModel = homeViewModel
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (viewModel.uiState.value) {
                // loading
                LoadingSearchState.Loading -> {
                    BallProgress()
                }
                // show menu list
                LoadingSearchState.NotLoading -> {
                    LazyColumn(
                        state = lazyListState,
                    ) {
                        itemsIndexed(viewModel.menuAllList) { index, item ->

                            // Menu to next page
                            viewModel.onChangeMenuScrollPosition(index)
                            if ((index + 1) >= viewModel.page.value * PAGE_SIZE && !viewModel.pageLoading.value) {
                                viewModel.onNextPage()
                            }

                            // Menu Card
                            MenuCardItem(
                                index = index,
                                model = item,
                                animationEnabled = viewModel.listAnim,
                                disableAnim = {
                                    viewModel.listAnim = false
                                }) {
                                navController.navigate(Screens.MenuDetail.createIdRoute(item.id))
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 100.dp,top = 12.dp)
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter),
                            ){
                                if (viewModel.pageLoading.value) {
                                    BallPulseSyncProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .padding(bottom = 12.dp),
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