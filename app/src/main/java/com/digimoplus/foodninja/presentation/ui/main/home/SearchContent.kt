@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.SearchChips
import com.digimoplus.foodninja.presentation.components.util.buttonDisabledGradient
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.SearchCategory

@Composable
fun SearchContent(
    viewModel: HomeViewModel,
    showBottomTab: (visibility: Boolean) -> Unit,
) {

    val searchListState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = searchListState,
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                HomeHeader(viewModel = viewModel)
            }

            item {
                SearchBody(viewModel)
            }
        }
        viewModel.checkEnabledButton()
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)) {
            GradientButton(
                enabled = viewModel.searchButtonEnable.value,
                modifier = Modifier
                    .fillMaxWidth(),
                enableGradient = buttonEnabledGradient(),
                disableGradient = buttonDisabledGradient(),
                text = "Search",
                textColor = getButtonTextColor(viewModel.searchButtonEnable.value),
                padding = PaddingValues(bottom = AppTheme.dimensions.grid_2),
                border = BorderStroke(width = 1.dp, color = AppTheme.colors.primary)
            ) {
                showBottomTab(true)
                if (viewModel.searchTypeFilter.value == SearchCategory.Restaurant) {
                    viewModel.pageState.value = HomePageState.RestaurantContent
                } else {
                    viewModel.pageState.value = HomePageState.MenuContent
                }
            }
        }
    }
}

@Composable
fun SearchBody(viewModel: HomeViewModel) {
    Column {

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_2))

        Text(text = "Type", color = AppTheme.colors.titleText, style = AppTheme.typography.h7)

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))

        LazyRow {
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.Restaurant,
                    selectedChips = viewModel.searchTypeFilter.value,
                ) {
                    viewModel.searchTypeFilter.value = it
                }
            }
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.Menu,
                    selectedChips = viewModel.searchTypeFilter.value,
                ) {
                    viewModel.searchTypeFilter.value = it
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_2))

        Text(text = "Location", color = AppTheme.colors.titleText, style = AppTheme.typography.h7)

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))

        LazyRow {
            item {
                SearchChips(
                    chipsValue = SearchCategory.OneKm,
                    selectedChips = viewModel.searchLocationFilter.value,
                ) {
                    selectChips(viewModel.searchLocationFilter, it)
                }
            }
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.GreaterThanTen,
                    selectedChips = viewModel.searchLocationFilter.value,
                ) {
                    selectChips(viewModel.searchLocationFilter, it)
                }
            }
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.LessThanTen,
                    selectedChips = viewModel.searchLocationFilter.value,
                ) {
                    selectChips(viewModel.searchLocationFilter, it)
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_2))

        Text(text = "Food", color = AppTheme.colors.titleText, style = AppTheme.typography.h7)

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))

        LazyRow {
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.Cake,
                    selectedChips = viewModel.searchFoodFilter.value,
                ) {
                    selectChips(viewModel.searchFoodFilter, it)
                }
            }
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.Soup,
                    selectedChips = viewModel.searchFoodFilter.value,
                ) {
                    selectChips(viewModel.searchFoodFilter, it)
                }
            }
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.MainCourse,
                    selectedChips = viewModel.searchFoodFilter.value,
                ) {
                    selectChips(viewModel.searchFoodFilter, it)
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))

        LazyRow {
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.Appetizer,
                    selectedChips = viewModel.searchFoodFilter.value,
                ) {
                    selectChips(viewModel.searchFoodFilter, it)
                }
            }
            item {
                SearchChips(
                    chipsValue =
                    SearchCategory.Dessert,
                    selectedChips = viewModel.searchFoodFilter.value,
                ) {
                    selectChips(viewModel.searchFoodFilter, it)
                }
            }
        }

    }
}

// save clicked chips to view model
private fun selectChips(isPress: MutableState<SearchCategory>, chipsValue: SearchCategory) {
    if (isPress.value == chipsValue) {
        isPress.value = SearchCategory.None
    } else {
        isPress.value = chipsValue
    }
}

@Composable
private fun getButtonTextColor(searchButtonEnable: Boolean): Color {
    return when {
        AppTheme.colors.isLight && searchButtonEnable -> Color.White
        AppTheme.colors.isLight -> Color.Black
        AppTheme.colors.isLight -> Color.White
        else -> Color.White
    }
}

