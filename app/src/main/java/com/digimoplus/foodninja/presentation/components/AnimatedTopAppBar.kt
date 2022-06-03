package com.digimoplus.foodninja.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.ui.main.home.HomeHeader
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import com.digimoplus.foodninja.presentation.ui.main.home.menu_content.HomeMenuViewModel
import com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content.HomeRestaurantViewModel

// animated search app bar & title & notification icon
@Composable
fun AnimatedTopAppBar(
    viewModel: HomeRestaurantViewModel,
    homeViewModel: HomeViewModel,
    focusRequester: FocusRequester,
    content: @Composable () -> Unit,
) {

    val scrollUpState = viewModel.scrollUp.observeAsState()

    // animations for AppBar
    // translation appBar animation
    val searchBarPosition by animateFloatAsState(if (scrollUpState.value == true) -312f else 0f)
    // grid view padding top
    val gridViewPaddingTop by animateDpAsState(if (scrollUpState.value == true) 104.dp else 200.dp)
    // search box padding top
    val searchBoxPadding by animateDpAsState(if (scrollUpState.value == true) 40.dp else 24.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = gridViewPaddingTop)
        ) {
            content()
        }

        HomeHeader(
            viewModel = homeViewModel,
            modifier = Modifier
                .graphicsLayer {
                    // move layout to top
                    translationY = (searchBarPosition)
                }
                .align(Alignment.TopCenter),
            searchTopPadding = searchBoxPadding,
            focusRequester = focusRequester,
            searchQuery = {
                viewModel.searchQuery(it)
            }
        )
    }
}

@Composable
fun AnimatedTopAppBar(
    viewModel: HomeMenuViewModel,
    homeViewModel: HomeViewModel,
    content: @Composable () -> Unit,
) {

    val scrollUpState = viewModel.scrollUp.observeAsState()

    // animations for AppBar
    // translation appBar animation
    val searchBarPosition by animateFloatAsState(if (scrollUpState.value == true) -312f else 0f)
    // content padding top
    val contentPaddingTop by animateDpAsState(if (scrollUpState.value == true) 104.dp else 200.dp)
    // search box padding top
    val searchBoxPadding by animateDpAsState(if (scrollUpState.value == true) 40.dp else 24.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = contentPaddingTop)
        ) {
            content()
        }

        HomeHeader(
            viewModel = homeViewModel,
            modifier = Modifier
                .graphicsLayer {
                    // move layout to top
                    translationY = (searchBarPosition)
                }
                .align(Alignment.TopCenter),
            searchTopPadding = searchBoxPadding,
            searchQuery = {
                viewModel.searchQuery(it)
            }
        )
    }
}