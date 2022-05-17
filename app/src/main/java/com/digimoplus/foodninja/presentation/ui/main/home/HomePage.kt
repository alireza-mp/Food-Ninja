@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.restaurantListPaddingBottom
import com.digimoplus.foodninja.repository.PAGE_SIZE
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator


@Composable
fun HomePage() {
    val viewModel: HomeViewModel = viewModel()

    when (viewModel.pageState.value) {

        HomePageState.MainContent -> {
            MainContent(viewModel = viewModel)
        }

        HomePageState.RestaurantContent -> {
            RestaurantContent(viewModel = viewModel)
        }

        HomePageState.MenuContent -> {
            MenuContent(viewModel = viewModel)
        }

    }
}

@Composable
private fun MainContent(viewModel: HomeViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            HomeHeader(viewModel = viewModel)
        }

        item {
            HomeBody(viewModel = viewModel)
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

@Composable
private fun RestaurantContent(viewModel: HomeViewModel) {
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

@Composable
private fun MenuContent(viewModel: HomeViewModel) {

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

// Header used into all pages home display
@Composable
private fun HomeHeader(viewModel: HomeViewModel) {
    Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_5))

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()) {

        Text(text = "Find Your Favorite Food",
            lineHeight = 40.sp,
            color = AppTheme.colors.titleText,
            modifier = Modifier.width(250.dp),
            style = AppTheme.typography.h4)

        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center) {
            Button(onClick = {

            },
                contentPadding = PaddingValues(horizontal = AppTheme.dimensions.grid_2,
                    vertical = 14.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .defaultMinSize(1.dp, 1.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.surface),
                shape = RoundedCornerShape(20.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_notifiaction),
                    contentDescription = "",
                    tint = AppTheme.colors.primary
                )
            }
        }

    }

    Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround) {

        val text by viewModel.search.collectAsState()

        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth(0.84f)
                .padding(end = AppTheme.dimensions.grid_1),
            shape = RoundedCornerShape(18.dp),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = AppTheme.colors.secondary,
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.secondaryText,
                disabledTextColor = Color.Transparent,
                backgroundColor = AppTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = AppTheme.colors.secondary
            ),
            textStyle = AppTheme.typography.body1,
            singleLine = true,
            placeholder = {
                Text(text = "What do you want to order?",
                    color = AppTheme.colors.secondaryText,
                    modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_0_25),
                    style = AppTheme.typography.body1)
            }, onValueChange = {
                viewModel.search.value = it
            })

        Button(onClick = { /*TODO*/ },
            contentPadding = PaddingValues(horizontal = 16.dp,
                vertical = 14.dp),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.onSecondary),
            shape = RoundedCornerShape(18.dp)) {

            Icon(painter = painterResource(id = R.drawable.ic_filtter_light),
                tint = AppTheme.colors.secondary,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_0_25),
                contentDescription = ""
            )
        }

    }

}
