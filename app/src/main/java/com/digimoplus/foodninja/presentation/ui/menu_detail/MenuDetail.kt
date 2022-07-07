@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.menu_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.util.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.UiState
import kotlinx.coroutines.*


@Composable
fun MenuDetailPage(
    navController: NavController,
    menuId: Int,
) {
    val viewModel: MenuDetailViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            viewModel.getDetails(menuId)
        }
    }

    MenuDetail(
        viewModel = viewModel,
        menuId = menuId,
        navController = navController,
        coroutineScope = coroutineScope
    )
}

@Composable
private fun MenuDetail(
    viewModel: MenuDetailViewModel,
    menuId: Int,
    navController: NavController,
    coroutineScope: CoroutineScope,
) {
    when (viewModel.loading.value) {
        UiState.Visible -> {
            // show details
            Details(
                viewModel = viewModel,
                navController = navController,
                coroutineScope = coroutineScope
            )
        }
        UiState.Loading -> {
            // show progress bar page
            ShowProgress()
        }
        UiState.NoInternet -> {
            // show no internet page
            NoInternetContent {
                viewModel.loading.value = UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.getDetails(menuId)
                }
            }
        }
    }
}

@Composable
private fun Details(
    viewModel: MenuDetailViewModel,
    navController: NavController,
    coroutineScope: CoroutineScope,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background)) {
        Box(modifier = Modifier.fillMaxSize()) {

            val image = loadPictureNoneDefault(url = viewModel.menuInfo.imageDetail).value
            // animate background image alpha
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dps)
                        .animateAlpha(
                            delayMillis = 500,
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
                    Content(viewModel = viewModel)
                }
                items(count = viewModel.commentList.size) { index ->
                    CommentCardItem(
                        model = viewModel.commentList[index],
                        isLastItem = viewModel.commentList.size - 1 == index
                    )
                }
            }

            //add to chart button
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateAlpha(
                        delayMillis = 300,
                        durationMillis = 700
                    )
                    .height(80.dp)
                    .padding(start = 6.dp, end = 6.dp, bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(15.dp),
            ) {
                if (viewModel.basketCount.value == 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = buttonEnabledGradient())
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false),
                                onClick = viewModel::onPlus
                            ),
                        contentAlignment = Alignment.Center,

                        ) {
                        Text(
                            text = "Add To Chart",
                            color = Color.White,
                            style = AppTheme.typography.h7,
                        )
                    }
                } else {
                    Row(modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically) {
                        basketNumber(
                            text = viewModel.basketCount.value.toString(),
                            name = viewModel.menuInfo.name,
                            onMinus = viewModel::onMinus,
                            onPlus = viewModel::onPlus
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun basketNumber(
    text: String,
    name: String,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(start = 16.dp))
        Text(
            text = name,
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h7
        )
        Spacer(Modifier.weight(1f))
        Card(
            modifier = Modifier.size(36.dp),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(10.dp),
            onClick = onMinus
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = buttonEnabledGradient()),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus),
                    modifier = Modifier.size(18.dp),
                    contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.padding(end = 12.dp))
        Text(
            text = text,
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h7,
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(end = 12.dp))
        Card(
            modifier = Modifier.size(36.dp),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(10.dp),
            onClick = onPlus
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = buttonEnabledGradient()),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    modifier = Modifier.size(18.dp),
                    contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
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

//content
@Composable
private fun Content(
    viewModel: MenuDetailViewModel,
) {
    Spacer(modifier = Modifier.padding(top = 155.dps))

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
                TitleChips(title = "Popular")
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = viewModel.menuInfo.title,
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
                    text = viewModel.menuInfo.locationKm
                )
                Spacer(modifier = Modifier.padding(start = 24.dp))
                Image(painter = painterResource(id = R.drawable.ic_rate), contentDescription = null
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(
                    style = AppTheme.typography.body1,
                    color = Color.LightGray,
                    text = "${viewModel.menuInfo.rate} Rating"
                )
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                text = viewModel.menuInfo.descriptionTop,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))

            BulletText(
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                texts = viewModel.materialsList
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Text(
                text = viewModel.menuInfo.descriptionBottom,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                text = "Testimonials",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))

        }
    }
}
