package com.digimoplus.foodninja.presentation.ui.main.home.main_content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.components.MenuCardItemShimmer
import com.digimoplus.foodninja.presentation.navigation.Screens

@Composable
fun MenuListItem(
    navController: NavController,
    launchAnimState: MutableState<Float>,
    state: UiState,
    index: Int,
    count: Int,
    list: List<Menu>,
) {
    when (state) {
        UiState.Loading -> {
            MenuCardItemShimmer(
                launchAnimState = launchAnimState,
                index = index, count = count)

        }
        UiState.Visible -> {
            if (list.size == 6)
                MenuCardItem(
                    launchAnimState = launchAnimState,
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