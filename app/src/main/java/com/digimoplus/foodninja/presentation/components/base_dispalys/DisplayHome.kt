package com.digimoplus.foodninja.presentation.components.base_dispalys

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.components.MenuCardItemShimmer
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.components.RestaurantCardItemShimmer
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.home.HomeHeader
import com.digimoplus.foodninja.presentation.ui.home.HomeViewModel

@ExperimentalMaterialApi
@Composable
fun HomeDisplay(viewModel: HomeViewModel) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            HomeHeader(viewModel = viewModel)
        }

        item {
            Body(viewModel = viewModel)
        }

        if (viewModel.loadingMenu.value) {
            items(count = 5) { index ->
                MenuCardItemShimmer(index = index, count = 5)
            }
        } else {
            itemsIndexed(viewModel.menuList) { index, model ->
                MenuCardItem(
                    index = index,
                    model = model,
                    count = viewModel.menuList.size,
                ) { //onClick

                }
            }
        }

    }
}

@ExperimentalMaterialApi
@Composable
private fun Body(viewModel: HomeViewModel) {

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column() {

            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1_5))

            Image(
                painter = painterResource(id = R.drawable.home_baner),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Nearest Restaurant",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1)
                }
            }

            NearestRestaurantList(viewModel)

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Popular Menu",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun NearestRestaurantList(viewModel: HomeViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                if (viewModel.loadingRestaurant.value) {
                    items(count = 5) { index ->
                        RestaurantCardItemShimmer(index = index,
                            visibility = viewModel.loadingRestaurant.value)
                    }
                } else {
                    itemsIndexed(viewModel.restaurantList) { index, model ->
                        RestaurantCardItem(
                            index = index,
                            model = model)
                    }
                }
            }
        }
    }
}

