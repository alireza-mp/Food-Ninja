package com.digimoplus.foodninja.presentation.ui.main.home.home_menu_page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import navigateWithSaveState

@Composable
fun HomeMenuPage(
    navController: NavController,
) {

    val viewModel: HomeMenuViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val lazyListState = rememberLazyListState()

    changeScrollingListener(viewModel, lazyListState)

    HandleErrors(viewModel = viewModel)

    // handle backPress
    HandleBackPress(viewModel = viewModel, homeViewModel = homeViewModel)


    // top app bar
    AnimatedTopAppBar(
        viewModel = viewModel,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (viewModel.uiState) {
                // loading
                LoadingSearchState.Loading -> {
                    BallProgress()
                }
                // show menu list
                LoadingSearchState.NotLoading -> {
                    MenusContent(
                        modifier = Modifier.align(Alignment.BottomCenter), // progress modifier
                        lazyListState = lazyListState,
                        viewModel = viewModel,
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
    viewModel: HomeMenuViewModel,
    lazyListState: LazyListState,
) {
    viewModel.updateScrollPosition(lazyListState.firstVisibleItemIndex)
}


@Composable
private fun HandleErrors(viewModel: HomeMenuViewModel) {
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
private fun HandleBackPress(
    viewModel: HomeMenuViewModel,
    homeViewModel: HomeViewModel,
) {
    val focusManager = LocalFocusManager.current
    BackHandler(enabled = viewModel.backState.value) {
        focusManager.clearFocus()
        homeViewModel.search.value = ""
        if (viewModel.isSearchIng) {
            viewModel.resetList()
        }
    }
}

@Composable
fun MenusContent(
    lazyListState: LazyListState,
    viewModel: HomeMenuViewModel,
    modifier: Modifier = Modifier, // progress modifier
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.padding(top = if (viewModel.scrollUp.value == true) 12.dp else 20.dp),
        state = lazyListState,
    ) {
        itemsIndexed(viewModel.menuList) { index, item ->

            // Menu to next page
            viewModel.onChangeMenuScrollPosition(index)
            if (
                (index + 1) >= viewModel.page * Constants.MENU_PAGE_SIZE &&
                !viewModel.pageLoading
            ) {
                viewModel.onNextPage()
            }

            // Menu Card
            AnimatedMenuCardItem(
                index = index,
                model = item,
                animationEnabled = viewModel.listAnim,
                disableAnim = {
                    viewModel.listAnim = false
                },
                paddingValues = PaddingValues(
                    top = 19.dp,
                    bottom = 1.dp,
                    end = 20.dp,
                    start = 20.dp
                )
            ) {
                navController.navigateWithSaveState(Screens.MenuDetail.createIdRoute(item.id))
            }
        }
        item {
            Box(
                modifier = modifier
                    .padding(bottom = if (viewModel.isLastPage()) 100.dp else 150.dp, top = 12.dp)
                    .fillMaxWidth(),
            ) {
                if (viewModel.pageLoading) {
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
