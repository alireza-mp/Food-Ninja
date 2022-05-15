package com.digimoplus.foodninja.presentation.ui.home.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.RestaurantCardItem


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ProfilePage() {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        var anim = true

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1).size) {
                RestaurantCardItem(
                    index = it,
                    model = Restaurant(1, "Tdhufhd Dgid", "", "12 Min"),
                    count = 20,
                    animationEnabled = anim,
                    disableAnim = { anim = false })
            }
        }

    }
}