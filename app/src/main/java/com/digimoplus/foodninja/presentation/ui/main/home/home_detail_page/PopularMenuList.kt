package com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.components.MenuCardItemShimmer
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.navigation.Screens

@Composable
fun PopularMenuList(
    navController: NavController,
    launchAnimState: MutableState<Float>,
    state: UiState,
    index: Int,
    count: Int,
    list: List<Menu>,
) {
    Box(
        modifier = Modifier.animateAlpha(
            state = launchAnimState,
            delayMillis = 1000,
            durationMillis = 1000,
        ),
    ) {
        when (state) {
            UiState.Loading -> {
                MenuCardItemShimmer(
                    index = index, count = count)

            }
            UiState.Visible -> {
                if (list.size == 6)
                    MenuCardItem(
                        index = index,
                        model = list[index],
                        count = count,
                    ) { //onClick
                        navController.navigate(Screens.MenuDetail.createIdRoute(list[index].id))
                    }
            }
            else -> {}
        }
    }
}