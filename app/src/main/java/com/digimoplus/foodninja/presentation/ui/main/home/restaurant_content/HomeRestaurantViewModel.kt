package com.digimoplus.foodninja.presentation.ui.main.home.restaurant_content

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
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.domain.util.LoadingSearchState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import com.digimoplus.foodninja.data.repository.HomeRepositoryImpl
import com.digimoplus.foodninja.data.repository.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeRestaurantViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    // snack bar state
    var snackBarState: SnackbarHostState? = null

    // ui state // loading / notFound / not loading
    val uiState = mutableStateOf(LoadingSearchState.Loading)

    // restaurant list
    val restaurantAllList = mutableStateListOf<Restaurant>()

    // page number
    val page = mutableStateOf(1)

    // next page progress loading
    val pageLoading = mutableStateOf(false)

    // update position for check go to next page
    private var restaurantListScrollPosition = 0

    // token
    private var token = ""

    // on backPress listener
    val backState = mutableStateOf(false)

    // search state
    var searchIng = false

    // update topAppBar animation
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var lastScrollIndex = 0


    init {
        //get token and request for restaurants list
        viewModelScope.launch(Dispatchers.Main) {
            getToken()
            getAllRestaurantsList()
        }
    }


    //request for search from restaurants list
    fun searchQuery(query: String) {
        searchIng = true
        backState.value = true
        newSearch(query = query)
    }

    // remove search items and show all
    suspend fun resetList() {
        resetPage()
        getAllRestaurantsList()
    }

    // new search request to server with page number
    private fun newSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPage()
            uiState.value = LoadingSearchState.Loading
            when (val result = repository.restaurantSearch(
                token = token,
                search = query,
                page = 1
            )) {
                is DataState.Success -> {
                    restaurantAllList.addAll(result.data)
                    if (restaurantAllList.size == 0) {
                        uiState.value = LoadingSearchState.NotFound
                    } else {
                        uiState.value = LoadingSearchState.NotLoading
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

    //reset last page
    // clear list
    private suspend fun resetPage() {
        withContext(Dispatchers.Main) {
            repository.lastPage = -1
            restaurantListScrollPosition = 0
            page.value = 1
            restaurantAllList.clear()
        }
    }

    // update scrollUp for animated appbar
    fun updateScrollPosition(newScrollIndex: Int) {
        // check scrolling to top or bottom
        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    private suspend fun getToken() {
        withContext(Dispatchers.IO) {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()

        }
    }

    // get all restaurants list with page number
    private suspend fun getAllRestaurantsList() {
        if (restaurantAllList.size == 0 || searchIng) {
            uiState.value = LoadingSearchState.Loading
            withContext(Dispatchers.IO) {
                delay(1000)
                when (val result = repository.getAllRestaurantsList(token, page = 1)) {
                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            restaurantAllList.addAll(result.data)
                            uiState.value = LoadingSearchState.NotLoading
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

    // request  to next page
    fun onNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkIsLastPage()) {
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

    // update scroll position
    fun onChangeRestaurantsScrollPosition(position: Int) {
        restaurantListScrollPosition = position
    }

    // is last page
    private fun checkIsLastPage(): Boolean {
        return repository.lastPage > page.value
    }

}