@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.main_content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.components.RestaurantCardItemShimmer
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun NearestRestaurantList(viewModel: HomeMainViewModel, navController: NavController) {
    // update restaurant ui
    if (viewModel.loadingRestaurant.value == UiState.NoInternet) {
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentAlignment = Alignment.Center,
        ) {
            TextButton(
                onClick = {
                    viewModel.callRequests()
                }
            ) {
                Text(
                    text = "Failed! Tap to try Again",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.body1
                )
            }
        }
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(count = 6) { index ->
                RestaurantListItem(
                    state = viewModel.loadingRestaurant.value,
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
                        navController.navigate(Screens.RestaurantDetail.createIdRoute(id = list[index].id))
                    }
                )
        }
        else -> {}
    }
}