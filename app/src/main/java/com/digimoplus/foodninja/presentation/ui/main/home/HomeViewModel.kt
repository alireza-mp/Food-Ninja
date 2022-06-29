@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.SearchCategory
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    val launchAnimState = mutableStateOf(0f)

    val searchTypeFilter = mutableStateOf(SearchCategory.Restaurant)
    val searchFoodFilter = mutableStateOf(SearchCategory.None)
    val searchLocationFilter = mutableStateOf(SearchCategory.None)
    val pageState = mutableStateOf(HomePageState.MainContent)
    val search = mutableStateOf("")
    var enableRestaurantFocus = false
    var searchButtonEnable = mutableStateOf(false)


    fun checkEnabledButton() {
        searchButtonEnable.value =
            searchFoodFilter.value != SearchCategory.None || searchLocationFilter.value != SearchCategory.None
    }

}