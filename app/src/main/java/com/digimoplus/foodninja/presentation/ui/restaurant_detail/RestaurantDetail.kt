@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.restaurant_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.CommentCardItem
import com.digimoplus.foodninja.presentation.components.NoInternetContent
import com.digimoplus.foodninja.presentation.components.RestaurantDetailMenuItem
import com.digimoplus.foodninja.presentation.components.util.*
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.*


@Composable
fun RestaurantDetailPage(
    navController: NavController,
    restaurantId: Int,
) {
    val viewModel: RestaurantDetailViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            viewModel.getDetails(restaurantId)
        }
    }

    RestaurantDetail(
        viewModel = viewModel,
        restaurantId = restaurantId,
        navController = navController,
        coroutineScope = coroutineScope
    )
}

@Composable
private fun RestaurantDetail(
    viewModel: RestaurantDetailViewModel,
    restaurantId: Int,
    navController: NavController,
    coroutineScope: CoroutineScope,
) {
    when (viewModel.loading.value) {
        "false" -> {
            // show details
            Details(
                viewModel = viewModel,
                navController = navController
            )
        }
        "true" -> {
            // show progress bar page
            ShowProgress()
        }
        "no_internet" -> {
            // show no internet page
            NoInternetContent {
                viewModel.loading.value = "true"
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.getDetails(restaurantId)
                }
            }
        }
    }
}

@Composable
private fun Details(
    viewModel: RestaurantDetailViewModel,
    navController: NavController,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background)) {
        Box(modifier = Modifier.fillMaxSize()) {

            val image = loadPictureNoneDefault(url = viewModel.restoInfo.imgDetail).value
            val imageAnim = remember {
                mutableStateOf(0f)
            }
            // animate background image alpha
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dps)
                        .animateAlpha(state = imageAnim,
                            delayMillis = 0,
                            durationMillis = 1000
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            LaunchedEffect(Unit) {
                delay(500)
                imageAnim.value = 1f
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                item {
                    Content(viewModel, navController)
                }
                items(count = viewModel.commentList.size) { index ->
                    CommentCardItem(viewModel.commentList[index])
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
    Spacer(modifier = Modifier.padding(top = 120.dps))

    Card(
        backgroundColor = AppTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .animateToTop(
                durationMillis = 1000,
                delayMillis = 0
            ),
        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.background(brush = buttonEnabledGradient(), alpha = 0.2f,
                    shape = RoundedCornerShape(15.dp))) {
                    Text(text = "Popular",
                        modifier = Modifier
                            .textBrush(gradientText())
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = viewModel.restoInfo.title,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h4,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_map), contentDescription = null
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    style = AppTheme.typography.body1,
                    color = Color.LightGray,
                    text = viewModel.restoInfo.locationKm
                )
                Spacer(modifier = Modifier.padding(start = 24.dp))
                Image(painter = painterResource(id = R.drawable.ic_rate), contentDescription = null
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    style = AppTheme.typography.body1,
                    color = Color.LightGray,
                    text = "${viewModel.restoInfo.rate} Rating"
                )
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                text = viewModel.restoInfo.desc,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
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
                items(count = viewModel.menuList.size) { index ->
                    RestaurantDetailMenuItem(
                        model = viewModel.menuList[index],
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





