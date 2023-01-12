@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.restaurant_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.animateToTop
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.components.util.loadPictureNoneDefault
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun RestaurantDetailPage(
    navController: NavController,
    restaurantId: Int,
) {
    val viewModel: RestaurantDetailViewModel = hiltViewModel()

    // get details from server
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            viewModel.getDetails(restaurantId)
        }
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


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            val image = loadPictureNoneDefault(
                url = viewModel.restaurantDetails.value?.restaurantDetailInfo?.imgDetail ?: ""
            ).value

            // animate background image alpha
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dps)
                        .animateAlpha(
                            delayMillis = 0,
                            durationMillis = 1000
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                item {
                    Content(viewModel, navController)
                }
                items(count = commentList.size) { index ->
                    CommentCardItem(commentList[index])
                }
            }
        }
    }
}

@Composable
private fun ShowProgress() {
    Box(modifier = Modifier
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
) {
    val menuList = remember {
        viewModel.restaurantDetails.value?.restaurantDetailMenus ?: listOf()
    }
    val detailInfo = remember {
        viewModel.restaurantDetails.value?.restaurantDetailInfo
    }

    Spacer(modifier = Modifier.padding(top = 120.dps))
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
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)) {
            Spacer(modifier = Modifier.padding(top = 10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                TitleChips(title = "Popular")
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = detailInfo?.title ?: "",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h4,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    style = AppTheme.typography.body1,
                    color = Color.LightGray,
                    text = detailInfo?.locationKm ?: ""
                )
                Spacer(modifier = Modifier.padding(start = 24.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_rate),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    style = AppTheme.typography.body1,
                    color = Color.LightGray,
                    text = "${detailInfo?.rate ?: ""} Rating"
                )
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                text = detailInfo?.desc ?: "",
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Popular Menu",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                )
                TextButton(onClick = {
                    // onclick
                }) {
                    Text(text = "View More",
                        color = AppTheme.colors.primaryTextVariant,
                        style = AppTheme.typography.body1)
                }
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(count = menuList.size) { index ->
                    RestaurantDetailMenuItem(
                        model = menuList[index],
                        onClick = {
                            // on item clicked
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = "Testimonials",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))

        }
    }
}





