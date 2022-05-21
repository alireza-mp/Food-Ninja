@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.restaurantListPaddingBottom
import com.digimoplus.foodninja.repository.PAGE_SIZE
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator


@Composable
fun HomePage() {

    val viewModel: HomeViewModel = viewModel()
    val backHandler = remember { mutableStateOf(false) }

    BackHandler(backHandler.value) {
        viewModel.pageState.value = HomePageState.MainContent
    }

    when (viewModel.pageState.value) {

        HomePageState.MainContent -> {
            backHandler.value = false
            MainContent(viewModel = viewModel)
        }

        HomePageState.RestaurantContent -> {
            backHandler.value = true
            RestaurantContent(viewModel = viewModel)
        }

        HomePageState.MenuContent -> {
            backHandler.value = true
            MenuContent(viewModel = viewModel)
        }

    }
}

// Header used into all pages home display
@Composable
fun HomeHeader(viewModel: HomeViewModel) {
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

    Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround) {

        val text by viewModel.search.collectAsState()

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
                viewModel.search.value = it
            })

        Button(onClick = { /*TODO*/ },
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
