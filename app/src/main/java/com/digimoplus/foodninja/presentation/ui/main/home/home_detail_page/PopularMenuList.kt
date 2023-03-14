package com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.presentation.components.MenuCardItem
import com.digimoplus.foodninja.presentation.components.MenuCardItemShimmer
import com.digimoplus.foodninja.presentation.navigation.Screens
import navigateWithSaveState

@Composable
fun PopularMenuList(
    navController: NavController,
    isLoading: Boolean,
    index: Int,
    count: Int,
    model: Menu?,
) {

    if (isLoading) {
        MenuCardItemShimmer(
            paddingValues = PaddingValues(
                top = if (index == 0) 15.dp else 20.dp,
                bottom = if (count - 1 == index) 100.dp else 0.dp,
                start = 20.dp,
                end = 20.dp,
            )
        )
    } else {
        model?.let {
            MenuCardItem(
                model = model,
                paddingValues = PaddingValues(
                    top = if (index == 0) 15.dp else 20.dp,
                    bottom = if (count - 1 == index) 100.dp else 0.dp,
                    start = 20.dp,
                    end = 20.dp,
                )
            ) { //onClick
                navController.navigateWithSaveState(Screens.MenuDetail.createIdRoute(model.id))
            }
        }
    }

}