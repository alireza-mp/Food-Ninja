@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
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

@Composable
fun NearestRestaurantList(viewModel: HomeDetailViewModel, navController: NavController) {

    // update restaurant ui
    if (viewModel.uiState == UiState.NoInternet) {
        ListNoInternetItem(modifier = Modifier.height(200.dp)) {
            viewModel.refresh()
        }
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(count = 6) { index ->
                RestaurantListItem(
                    state = viewModel.uiState,
                    index = index,
                    list = viewModel.restaurantList,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun RestaurantListItem(
    state: UiState,
    index: Int,
    list: List<Restaurant>,
    navController: NavController,
) {
    when (state) {
        UiState.Loading -> {
            RestaurantCardItemShimmer()
        }
        UiState.Visible -> {
            if (list.size == 6)
                RestaurantCardItem(
                    model = list[index],
                    onClick = {
                        // navigate to restaurant Detail page & send id
                        navController.navigate(Screens.RestaurantDetail.createIdRoute(id = list[index].id)) {
                            restoreState = true
                        }
                    }
                )
        }
        else -> {}
    }
}