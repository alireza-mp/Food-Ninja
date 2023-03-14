@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.menu_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
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
fun MenuDetailPage(
    menuId: Int,
    navController: NavController,
) {
    val viewModel: MenuDetailViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getDetails(menuId)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        MenuDetail(
            viewModel = viewModel,
            menuId = menuId,
        )

        CustomSnackBar(
            snackBarHostState = viewModel.snackBarHost,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            viewModel.snackBarHost.currentSnackbarData?.dismiss()
        }
    }

}

@Composable
private fun MenuDetail(
    viewModel: MenuDetailViewModel,
    menuId: Int,
) {
    when (viewModel.uiState) {
        UiState.Visible -> {
            // show details
            Details(
                viewModel = viewModel,
            )
        }
        UiState.Loading -> {
            // show progress bar page
            ShowProgress()
        }
        UiState.NoInternet -> {
            // show no internet page
            NoInternetContent(
                onRetry = { viewModel.refresh(menuId) }
            )
        }
    }

    MenuDetailAlertDialog(
        state = viewModel.alertDialogVisibility,
        onNoClick = viewModel::alertDialogOnNo,
        onYesClick = viewModel::alertDialogOnYes
    )
}

@Composable
private fun Details(
    viewModel: MenuDetailViewModel,
) {
    val commentList = remember {
        viewModel.menuDetails?.menuDetailComments ?: listOf()
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
    ) {

        // animate background image alpha
        NetworkImage(
            url = viewModel.menuDetails?.menuDetailInfo?.imageDetail ?: "",
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
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Content(viewModel = viewModel, maxHeight)
            }
            itemsIndexed(items = commentList) { index, item ->
                CommentCardItem(
                    name = item.name,
                    imageUrl = item.imageUrl,
                    date = item.date,
                    rate = item.rate,
                    description = item.description,
                    isLastItem = commentList.size - 1 == index
                )
            }
        }

        //add to chart button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .coloredShadow(
                    color = Color(0xFF144E5A),
                    alpha = 0.12f,
                    offsetX = 8.dp,
                    offsetY = 8.dp,
                )
                .padding(horizontal = 20.dp)
                .animateAlpha(
                    delayMillis = 300,
                    durationMillis = 700
                )
                .height(80.dp)
                .padding(start = 6.dp, end = 6.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = AppTheme.colors.surface,
            elevation = 0.dp,
        ) {
            if (viewModel.basketCount == 0) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = buttonEnabledGradient())
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = viewModel::addToCart
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.padding(start = 16.dp))
                    Text(
                        text = viewModel.menuDetails?.menuDetailInfo?.name ?: "",
                        color = AppTheme.colors.titleText,
                        style = AppTheme.typography.h7
                    )
                    Spacer(Modifier.weight(1f))
                    BasketNumbers(
                        text = viewModel.basketCount.toString(),
                        onMinus = viewModel::onMinus,
                        onPlus = viewModel::onPlus
                    )
                    Spacer(modifier = Modifier.padding(start = 16.dp))
                }

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
        contentAlignment = Alignment.Center,
    ) {
        BallProgress()
    }
}

//content
@Composable
private fun Content(
    viewModel: MenuDetailViewModel,
    maxHeight: Dp,
) {
    val materialsList = remember {
        viewModel.menuDetails?.menuDetailMaterials ?: listOf()
    }
    Spacer(modifier = Modifier.padding(top = (maxHeight / 10) * 4))
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
        Column(modifier = Modifier.padding(vertical = 10.dp)) {

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
                text = viewModel.menuDetails?.menuDetailInfo?.title ?: "",
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
                    title = "${viewModel.menuDetails?.menuDetailInfo?.rate ?: ""} Rating",
                    icon = R.drawable.ic_rate,
                    space = 10.dp,
                )
                Spacer(modifier = Modifier.padding(start = 24.dp))
                InfoChips(
                    title = "2000+ Order",
                    icon = R.drawable.ic_shopping_bag,
                    space = 10.dp,
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = viewModel.menuDetails?.menuDetailInfo?.descriptionTop ?: "",
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))

            BulletText(
                modifier = Modifier.padding(horizontal = 20.dp),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                texts = materialsList
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = viewModel.menuDetails?.menuDetailInfo?.descriptionBottom ?: "",
                style = AppTheme.typography.body1,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Testimonials",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h7,
            )
        }
    }
}
