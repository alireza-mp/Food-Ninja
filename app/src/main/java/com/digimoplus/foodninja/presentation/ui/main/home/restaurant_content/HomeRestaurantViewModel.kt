@file:OptIn(FlowPreview::class)

package com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.presentation.util.LoadingSearchState
import com.digimoplus.foodninja.presentation.util.showSnackBarError
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import com.digimoplus.foodninja.repository.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeRestaurantViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    var snackBarState: SnackbarHostState? = null
    val loadingRestaurant = mutableStateOf(LoadingSearchState.Loading)
    val restaurantAllList = mutableStateListOf<Restaurant>()
    val page = mutableStateOf(1)
    val pageLoading = mutableStateOf(false)
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var restaurantListScrollPosition = 0
    private var token = ""
    private var lastScrollIndex = 0
    val backState = mutableStateOf(false)
    var searchIng = false

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getToken()
            getAllRestaurantsList()
        }
    }

    fun searchQuery(query: String) {
        searchIng = true
        backState.value = true
        newSearch(query = query)
    }

    suspend fun resetList() {
        resetPage()
        getAllRestaurantsList()
    }

    private fun newSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPage()
            loadingRestaurant.value = LoadingSearchState.Loading
            when (val result = repository.restaurantSearch(
                token = token,
                search = query,
                page = 1
            )) {
                is DataState.Success -> {
                    restaurantAllList.addAll(result.data)
                    if (restaurantAllList.size == 0) {
                        loadingRestaurant.value = LoadingSearchState.NotFound
                    } else {
                        loadingRestaurant.value = LoadingSearchState.NotLoading
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        result.showSnackBarError(snackBarState)
                    }
                }
            }
        }
    }

    private suspend fun resetPage() {
        withContext(Dispatchers.Main) {
            repository.lastPage = -1
            restaurantListScrollPosition = 0
            page.value = 1
            restaurantAllList.clear()
        }
    }

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return
        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    private suspend fun getToken() {
        withContext(Dispatchers.IO) {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()

        }
    }

    private suspend fun getAllRestaurantsList() {
        if (restaurantAllList.size == 0 || searchIng) {
            loadingRestaurant.value = LoadingSearchState.Loading
            withContext(Dispatchers.IO) {
                delay(1000)
                when (val result = repository.getAllRestaurantsList(token, page = 1)) {
                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            restaurantAllList.addAll(result.data)
                            loadingRestaurant.value = LoadingSearchState.NotLoading
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            result.showSnackBarError(snackBarState)
                        }
                    }
                }
            }
        }
    }

    fun onNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.lastPage > page.value) {
                if ((restaurantListScrollPosition + 1) >= page.value * PAGE_SIZE) {

                    pageLoading.value = true
                    page.value = page.value + 1 //incrementPage
                    delay(1000)

                    when (val result = repository.getAllRestaurantsList(token, page = page.value)) {
                        is DataState.Success -> {
                            restaurantAllList.addAll(result.data)
                            pageLoading.value = false
                        }
                        else -> {
                            page.value = page.value - 1
                            pageLoading.value = false
                            result.showSnackBarError(snackBarState)
                        }
                    }
                }
            }
        }
    }

    fun onChangeRestaurantsScrollPosition(position: Int) {
        restaurantListScrollPosition = position
    }

    fun checkIsLastPage(): Boolean {
        return repository.lastPage == page.value
    }

}