@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.components.MenuCardItemShimmer
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.components.RestaurantCardItemShimmer
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.HomePageState

@Composable
fun MainContent(viewModel: HomeViewModel) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            HomeHeader(viewModel = viewModel)
        }

        item {
            HomeBody(viewModel = viewModel)
        }

        items(count = 5) { index ->
            MenuListItem(state = viewModel.loadingMenu.value,
                index = index,
                count = 5,
                list = viewModel.menuList)
        }
    }
}

@Composable
private fun HomeBody(viewModel: HomeViewModel) {

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
                TextButton(onClick = {
                    viewModel.pageState.value = HomePageState.RestaurantContent
                    viewModel.getAllRestaurantsList()
                }) {
                    Text(text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1)
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    NearestRestaurantList(viewModel = viewModel)
                }
            }

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

@Composable
private fun NearestRestaurantList(viewModel: HomeViewModel) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(count = 6) { index ->
            RestaurantListItem(
                loading = viewModel.loadingRestaurant.value,
                index = index,
                list = viewModel.restaurantList)

        }
    }
}

@Composable
fun RestaurantListItem(loading: Boolean, index: Int, list: List<Restaurant>) {

    if (loading) {
        RestaurantCardItemShimmer(index = index)
    } else {
        RestaurantCardItem(
            index = index,
            model = list[index])
    }


}

@Composable
fun MenuListItem(state: Boolean, index: Int, count: Int, list: List<Menu>) {
    if (state) {
        MenuCardItemShimmer(index = index, count = count)
    } else {
        MenuCardItem(
            index = index,
            model = list[index],
            count = count,
        ) { //onClick

        }
    }
}