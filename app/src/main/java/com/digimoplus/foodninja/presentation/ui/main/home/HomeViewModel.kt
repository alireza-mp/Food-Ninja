package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.SearchCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor() : ViewModel() {

    // alpha animation when display launched
    val launchAnimState = mutableStateOf(0f)

    // search content categories states
    val searchTypeFilter = mutableStateOf(SearchCategory.Restaurant)
    val searchFoodFilter = mutableStateOf(SearchCategory.None)
    val searchLocationFilter = mutableStateOf(SearchCategory.None)

    // display state : main content / restaurant content / menu content / search content
    val pageState = mutableStateOf(HomePageState.MainContent)

    //search text
    val search = mutableStateOf("")

    // default search focus when clicked on search in main content
    var enableRestaurantFocus = false

    // bottom button in search content enabled
    var searchButtonEnable = mutableStateOf(false)

    // content anim in restaurant content
    var contentListAnim = true

    // check is enabled bottom button in search content
    fun checkEnabledButton() {
        searchButtonEnable.value =
            searchFoodFilter.value != SearchCategory.None || searchLocationFilter.value != SearchCategory.None
    }

}