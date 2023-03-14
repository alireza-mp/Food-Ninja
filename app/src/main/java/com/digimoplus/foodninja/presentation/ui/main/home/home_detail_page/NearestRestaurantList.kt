@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.ListNoInternetItem
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.components.RestaurantCardItemShimmer
import com.digimoplus.foodninja.presentation.navigation.Screens
import navigateWithSaveState

@Composable
fun NearestRestaurantList(viewModel: HomeDetailViewModel, navController: NavController) {

    // update restaurant ui
    if (viewModel.uiState == UiState.NoInternet) {
        ListNoInternetItem(modifier = Modifier.height(200.dp)) {
            viewModel.refresh()
        }
    } else {
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(
                items = viewModel.restaurantList,
                key = { index, _ -> index }) { index, item ->
                RestaurantListItem(
                    isLoading = viewModel.uiState == UiState.Loading,
                    model = item,
                    navController = navController,
                    index = index,
                )
            }
        }
    }
}

@Composable
private fun RestaurantListItem(
    isLoading: Boolean,
    model: Restaurant?,
    index: Int,
    navController: NavController,
) {
    if (isLoading) {
        RestaurantCardItemShimmer(
            paddingValues = PaddingValues(
                top = 16.dp,
                bottom = 20.dp,
                end = 19.dp,
                start = if (index == 0) 20.dp else 1.dp,
            )
        )
    } else {
        model?.let {
            RestaurantCardItem(
                model = model,
                onClick = {
                    // navigate to restaurant Detail page & send id
                    navController.navigateWithSaveState(Screens.RestaurantDetail.createIdRoute(id = model.id))
                },
                paddingValues = PaddingValues(
                    top = 16.dp,
                    bottom = 20.dp,
                    end = 19.dp,
                    start = if (index == 0) 20.dp else 1.dp,
                )
            )
        }
    }
}