package com.digimoplus.foodninja.presentation.components.bottom

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.domain.util.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CustomBottomNavigation(
    pagerState: PagerState,
) {

    val coroutineScope = rememberCoroutineScope()
    val bottomNavigationState = remember {
        mutableStateOf(BottomNavigationModel(tab1 = true, tab2 = false, tab3 = false))
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp), contentAlignment = Alignment.Center
    ) {


        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            NavTab(state = bottomNavigationState.value.tab1, id = TAB_ICON_HOME) {
                bottomNavigationState.value =
                    BottomNavigationModel(tab1 = true, tab2 = false, tab3 = false)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }

            NavTab(state = bottomNavigationState.value.tab2, id = TAB_ICON_PROFILE) {
                bottomNavigationState.value =
                    BottomNavigationModel(tab1 = false, tab2 = true, tab3 = false)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(1)
                }
            }
            // basket just select tab
            NavTab {
                Log.d(Constants.TAG, "test: ")
            }
            NavTab(state = bottomNavigationState.value.tab3, id = TAB_ICON_CHAT) {
                bottomNavigationState.value =
                    BottomNavigationModel(tab1 = false, tab2 = false, tab3 = true)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(2)
                }
            }
        }

    }
}