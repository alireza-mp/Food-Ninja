@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.main_content.MainContent
import com.digimoplus.foodninja.presentation.ui.main.home.menu_content.MenuContent
import com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content.RestaurantContent
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.SearchCategory
import kotlinx.coroutines.launch


@Composable
fun HomePage(snackBarHostState: SnackbarHostState) {

    val homeViewModel: HomeViewModel = viewModel()
    val backHandler = remember { mutableStateOf(false) }

    BackHandler(backHandler.value) {
        when (homeViewModel.pageState.value) {
            HomePageState.RestaurantContent -> {
                homeViewModel.pageState.value = HomePageState.MainContent
            }
            HomePageState.MenuContent -> {
                homeViewModel.pageState.value = HomePageState.MainContent
            }
            HomePageState.SearchContent -> {
                if (homeViewModel.searchTypeFilter.value == SearchCategory.Restaurant) {
                    homeViewModel.pageState.value = HomePageState.RestaurantContent
                } else {
                    homeViewModel.pageState.value = HomePageState.MenuContent
                }
            }
            else -> {
                // never
            }
        }
    }

    when (homeViewModel.pageState.value) {

        HomePageState.MainContent -> {
            backHandler.value = false
            MainContent(
                homeViewModel = homeViewModel,
                snackBarHostState = snackBarHostState
            )
        }

        HomePageState.RestaurantContent -> {
            backHandler.value = true
            RestaurantContent(
                homeViewModel = homeViewModel,
                snackBarHostState = snackBarHostState
            )
        }

        HomePageState.MenuContent -> {
            backHandler.value = true
            MenuContent(
                homeViewModel = homeViewModel,
                snackBarHostState = snackBarHostState
            )
        }

        HomePageState.SearchContent -> {
            backHandler.value = true
            SearchContent(viewModel = homeViewModel)
        }

    }
}

// homeViewModel
// Header used into all pages home display
@Composable
fun HomeHeader(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    searchTopPadding: Dp = AppTheme.dimensions.grid_3,
    listState: LazyListState? = null,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {

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

        Spacer(modifier = Modifier.padding(top = searchTopPadding))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {

            val text by viewModel.searchValue.collectAsState()

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
                    viewModel.searchValue.value = it
                })

            Button(onClick = {
                coroutineScope.launch {
                    listState?.animateScrollToItem(0)
                    viewModel.pageState.value = HomePageState.SearchContent
                }
            },
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
}
