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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.SearchAppBar
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.main_content.MainContent
import com.digimoplus.foodninja.presentation.ui.main.home.menu_content.MenuContent
import com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content.RestaurantContent
import com.digimoplus.foodninja.presentation.util.HomePageState
import kotlinx.coroutines.delay


@Composable
fun HomePage(
    snackBarHostState: SnackbarHostState,
    showBottomTab: (visibility: Boolean) -> Unit,
    navController: NavController
) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val backHandler = remember { mutableStateOf(false) }

    BackHandler(backHandler.value) {
        homeViewModel.contentListAnim = true
        when (homeViewModel.pageState.value) {
            HomePageState.RestaurantContent -> {
                homeViewModel.pageState.value = HomePageState.MainContent
            }
            HomePageState.MenuContent -> {
                homeViewModel.pageState.value = HomePageState.MainContent
            }
            HomePageState.SearchContent -> {
                showBottomTab(true)
                homeViewModel.pageState.value = HomePageState.MainContent
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
                snackBarHostState = snackBarHostState,
                navController = navController
            )
        }

        HomePageState.RestaurantContent -> {
            backHandler.value = true
            RestaurantContent(
                homeViewModel = homeViewModel,
                snackBarHostState = snackBarHostState,
                navController = navController
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
            showBottomTab(false)
            SearchContent(viewModel = homeViewModel, showBottomTab = showBottomTab)
        }

    }

    LaunchedEffect(Unit) {
        delay(500)
        homeViewModel.launchAnimState.value = 1f
    }

}

// homeViewModel
// Header used into all pages home display
@Composable
fun HomeHeader(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    searchTopPadding: Dp = 24.dp,
    listState: LazyListState? = null,
    searchQuery: (query: String) -> Unit = {},
    focusRequester: FocusRequester? = null,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.padding(top = 40.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .animateAlpha(state = viewModel.launchAnimState, durationMillis = 1000)) {

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
                    contentPadding = PaddingValues(horizontal = 16.dp,
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

        SearchAppBar(
            viewModel = viewModel,
            coroutineScope = coroutineScope,
            listState = listState,
            focusRequester = focusRequester,
            searchQuery = {
                searchQuery(it)
            }
        )

    }
}
