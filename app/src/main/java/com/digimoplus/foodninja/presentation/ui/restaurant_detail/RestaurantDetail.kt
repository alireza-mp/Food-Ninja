@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.restaurant_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.util.*
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun RestaurantDetailPage(
    navController: NavController,
    restaurantId: Int,
) {
    val viewModel: RestaurantDetailViewModel = hiltViewModel()

    // get details from server
    LaunchedEffect(Unit) {
        viewModel.getDetails(restaurantId)
    }

    RestaurantDetail(
        viewModel = viewModel,
        restaurantId = restaurantId,
        navController = navController,
    )
}

@Composable
private fun RestaurantDetail(
    viewModel: RestaurantDetailViewModel,
    restaurantId: Int,
    navController: NavController,
) {
    // update ui
    when (viewModel.uiState) {
        UiState.Visible -> {
            // show details
            Details(
                viewModel = viewModel,
                navController = navController
            )
        }
        UiState.Loading -> {
            // show progress bar page
            ShowProgress()
        }
        UiState.NoInternet -> {
            // show no internet page
            NoInternetContent {
                viewModel.refresh(restaurantId)
            }
        }
    }
}

@Composable
private fun Details(
    viewModel: RestaurantDetailViewModel,
    navController: NavController,
) {
    val commentList = remember {
        viewModel.restaurantDetails.value?.restaurantDetailComment ?: listOf()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
    ) {
        // animate background image alpha
        NetworkImage(
            url = viewModel.restaurantDetails.value?.restaurantDetailInfo?.imgDetail ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .animateAlpha(
                    delayMillis = 0,
                    durationMillis = 1000
                ),
            cornerRadius = 0.dp,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            item {
                Content(viewModel, navController, maxHeight)
            }
            itemsIndexed(items = commentList) { _, item ->
                CommentCardItem(
                    name = item.name,
                    imageUrl = item.imageUrl,
                    date = item.date,
                    rate = item.rate,
                    description = item.description,
                )
            }
        }
    }
}

@Composable
private fun ShowProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        BallProgress()
    }
}

@Composable
private fun Content(
    viewModel: RestaurantDetailViewModel,
    navController: NavController,
    maxHeight: Dp,
) {
    val menuList = remember {
        viewModel.restaurantDetails.value?.restaurantDetailMenus ?: listOf()
    }
    val detailInfo = remember {
        viewModel.restaurantDetails.value?.restaurantDetailInfo
    }

    Spacer(modifier = Modifier.padding(top = (maxHeight / 10) * 4))
    Box {

    }
    Card(
        backgroundColor = AppTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .animateToTop(
                durationMillis = 1000,
                delayMillis = 0
            ),
        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
        ) {

            Spacer(modifier = Modifier.padding(top = 18.dp))

            DragChips()

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TitleChips(title = "Popular")
                Spacer(modifier = Modifier.weight(1f))
                ClickableChips(icon = R.drawable.ic_map, color = Color(0xFF53E88B)) {
                    // onClick
                }
                Spacer(modifier = Modifier.padding(end = 10.dp))
                ClickableChips(icon = R.drawable.ic_like, color = Color(0xFFFF1D1D)) {
                    // onClick
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = detailInfo?.title ?: "",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h4,
                fontSize = 27.sp,
                fontWeight = FontWeight.W400,
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                InfoChips(
                    title = detailInfo?.locationKm ?: "",
                    icon = R.drawable.ic_map,
                    space = 10.dp,
                )
                Spacer(modifier = Modifier.padding(start = 24.dp))
                InfoChips(
                    title = "${detailInfo?.rate ?: ""} Rating",
                    icon = R.drawable.ic_rate,
                    space = 10.dp,
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = detailInfo?.desc ?: "",
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Popular Menu",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                )
                TextButton(
                    onClick = {
                        // onclick
                    },
                ) {
                    Text(
                        text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(items = menuList) { index, item ->
                    RestaurantDetailMenuItem(
                        model = item,
                        padding = PaddingValues(
                            start = if (index == 0) 20.dp else 1.dp,
                            end = 19.dp
                        ),
                        onClick = {
                            // on item clicked
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Testimonials",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
        }
    }
}





