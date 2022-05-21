@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.components.CircleBallProgress
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.restaurantListPaddingBottom
import com.digimoplus.foodninja.repository.PAGE_SIZE
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator

@Composable
fun RestaurantContent(viewModel: HomeViewModel) {
    val state = rememberLazyGridState()

    Column {
        HomeHeader(viewModel = viewModel)
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {

            if (!viewModel.loadingRestaurant.value) {
                LazyVerticalGrid(
                    state = state,
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
}
