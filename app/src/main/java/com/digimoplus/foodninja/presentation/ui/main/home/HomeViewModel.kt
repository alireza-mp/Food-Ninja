@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.digimoplus.foodninja.presentation.ui.main.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.presentation.util.HomePageState
import com.digimoplus.foodninja.presentation.util.SearchCategory
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    val searchTypeFilter = mutableStateOf(SearchCategory.Restaurant)
    val searchFoodFilter = mutableStateOf(SearchCategory.None)
    val searchLocationFilter = mutableStateOf(SearchCategory.None)
    val pageState = mutableStateOf(HomePageState.MainContent)
    val searchValue = MutableStateFlow("")





}