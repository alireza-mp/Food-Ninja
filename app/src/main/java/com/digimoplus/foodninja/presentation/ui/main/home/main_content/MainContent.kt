@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home.main_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.components.MenuCardItemShimmer
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem
import com.digimoplus.foodninja.presentation.components.RestaurantCardItemShimmer
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeHeader
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.digimoplus.foodninja.presentation.util.HomePageState
import kotlinx.coroutines.launch

@Composable
fun MainContent(
    homeViewModel: HomeViewModel,
    snackBarHostState: SnackbarHostState,
) {

    val viewModel: HomeMainViewModel = viewModel()
    viewModel.snackBarState = snackBarHostState
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            HomeHeader(viewModel = homeViewModel, listState = lazyListState)
        }

        item {
            HomeBody(viewModel = viewModel,
                listState = lazyListState,
                pageState = homeViewModel.pageState)
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
private fun HomeBody(
    viewModel: HomeMainViewModel,
    listState: LazyListState,
    pageState: MutableState<HomePageState>,
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column {

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
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                        pageState.value = HomePageState.RestaurantContent
                    }
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
private fun NearestRestaurantList(viewModel: HomeMainViewModel) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(count = 6) { index ->
            RestaurantListItem(
                loading = viewModel.loadingRestaurant.value,
                index = index,
                list = viewModel.restaurantList
            )
        }
    }
}

@Composable
fun RestaurantListItem(loading: Boolean, index: Int, list: List<Restaurant>) {

    if (loading) {
        RestaurantCardItemShimmer(index = index)
    } else {
        if (list.size == 6)
            RestaurantCardItem(
                index = index,
                model = list[index]
            )
    }


}

@Composable
fun MenuListItem(state: Boolean, index: Int, count: Int, list: List<Menu>) {
    if (state) {
        MenuCardItemShimmer(index = index, count = count)
    } else {
        if (list.size == 5)
            MenuCardItem(
                index = index,
                model = list[index],
                count = count,
            ) { //onClick

            }
    }
}