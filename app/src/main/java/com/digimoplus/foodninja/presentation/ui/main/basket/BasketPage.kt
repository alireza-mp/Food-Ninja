package com.digimoplus.foodninja.presentation.ui.main.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.AnimatableBasketCardItem
import com.digimoplus.foodninja.presentation.components.BackButton
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.components.util.textBrush
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun BasketPage(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel: BasketViewModel = hiltViewModel()

    // get basket list from database
    LaunchedEffect(Unit) {
        viewModel.getBasketList()
    }

    // set background image to page
    PageMainBackgroundImage(
        lightBackground = R.drawable.main_page_background_light,
        darkBackground = R.drawable.main_page_background_dark,
        snackBarState = viewModel.snackBarState,
        paddingValues = PaddingValues(
            start = 16.dp,
            end = 16.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            // basket list
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                // page header
                item { HeaderContent(navController) }

                // list items
                itemsIndexed(
                    items = viewModel.basketList,
                    key = { _, item -> item.id }) { index, item ->

                    AnimatableBasketCardItem(
                        item = item,
                        coroutineScope = coroutineScope,
                        enableBottomPadding = index != viewModel.basketList.size - 1,
                        onDelete = { id -> viewModel.onDelete(id) },
                        onItemCount = { id, count -> viewModel.onItemCount(id, count) },
                    )
                }
            }

            // if basket empty show empty content
            if (viewModel.basketList.isEmpty()) {
                BasketEmptyContent(viewModel, navController)
            }

            // if basket not empty show pay card to bottom
            if (viewModel.basketList.isNotEmpty()) {
                Box(modifier = Modifier
                    .align(Alignment.BottomCenter)
                ) {
                    PayContent(viewModel = viewModel)
                }
            }

        }

    }
}

@Composable
private fun BasketEmptyContent(viewModel: BasketViewModel, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val empty by rememberLottieComposition(LottieCompositionSpec.RawRes(
            R.raw.empty))

        LottieAnimation(
            modifier = Modifier.size(260.dp),
            composition = empty,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))
        Text(
            text = "Your basket is empty",
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h4M,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextButton(onClick = {
            navController.navigate(Screens.Main.route)
        }) {
            Text(text = "Tap to order now",
                color = AppTheme.colors.primaryText,
                style = AppTheme.typography.body
            )
        }
    }
}

@Composable
private fun PayContent(viewModel: BasketViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp
            )
            .height(230.dp),
        elevation = 15.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush = buttonEnabledGradient())
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.basket_cash_background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.padding(top = 14.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Sub-Total",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "${viewModel.totalPrice} $",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Delivery Charge",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "20 $",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Discount",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "0 $",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.padding(top = 25.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Total",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "${(viewModel.totalPrice + 20)} $",
                        color = Color.White,
                        style = AppTheme.typography.h7,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Button(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = {
                        // on pay clicked
                    }) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Text(
                            modifier = Modifier
                                .textBrush(gradientText()),
                            text = "Place My Order",
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.h7,
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun HeaderContent(navController: NavController) {

    Column {
        Spacer(modifier = Modifier.padding(top = 24.dps))
        BackButton(
            paddingValues = PaddingValues(start = 0.dp), onClick = {
                navController.navigate(Screens.Main.route)
            })
        Spacer(modifier = Modifier.padding(top = 16.dp))

        Text(text = "Order details",
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h4
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))
    }


}