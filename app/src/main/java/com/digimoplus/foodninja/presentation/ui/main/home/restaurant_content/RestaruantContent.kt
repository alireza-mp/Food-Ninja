@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.components.CircleBallProgress
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeHeader
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.digimoplus.foodninja.presentation.util.restaurantListPaddingBottom
import com.digimoplus.foodninja.repository.PAGE_SIZE
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator

@Composable
fun RestaurantContent(viewModel: HomeViewModel) {

    val lazyGridState = rememberLazyGridState()
    val scrollUpState = viewModel.scrollUp.observeAsState()
    viewModel.updateScrollPosition(lazyGridState.firstVisibleItemIndex)

    // translation animation
    val searchBarPosition by animateFloatAsState(if (scrollUpState.value == true) -312f else 0f)
    // grid view padding top
    val gridViewPadding by animateDpAsState(if (scrollUpState.value == true) 104.dp else 200.dp)
    // search box padding top
    val searchBoxPadding by animateDpAsState(if (scrollUpState.value == true) 40.dp else 24.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = gridViewPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                if (!viewModel.loadingRestaurant.value) {
                    LazyVerticalGrid(
                        state = lazyGridState,
                        columns = GridCells.Fixed(2)
                    ) {
                        itemsIndexed(viewModel.restaurantAllList) { index, item ->

                            viewModel.onChangeRestaurantsScrollPosition(index)
                            if ((index + 1) >= viewModel.page.value * PAGE_SIZE && !viewModel.pageLoading.value) {
                                viewModel.onNextPage()
                            }

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
                } else {
                    CircleBallProgress()
                }
            }
        }

        HomeHeader(
            viewModel = viewModel,
            modifier = Modifier
                .graphicsLayer {
                    // move layout to top
                    translationY = (searchBarPosition)
                }
                .align(Alignment.TopCenter),
            searchTopPadding = searchBoxPadding
        )

    }


}
