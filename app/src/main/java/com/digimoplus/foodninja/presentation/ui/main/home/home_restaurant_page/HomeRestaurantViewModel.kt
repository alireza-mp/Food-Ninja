package com.digimoplus.foodninja.presentation.ui.main.home.home_restaurant_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.useCase.restaurants.GetRestaurantsListUseCase
import com.digimoplus.foodninja.domain.useCase.restaurants.SearchRestaurantUseCase
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.LoadingSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeRestaurantViewModel
@Inject
constructor(
    private val getRestaurantsListUseCase: GetRestaurantsListUseCase,
    private val searchRestaurantUseCase: SearchRestaurantUseCase,
) : ViewModel() {


    // ui state // loading / notFound / not loading
    var uiState by mutableStateOf(LoadingSearchState.Loading)
        private set

    var handleErrorsState by mutableStateOf<DataState<Any>>(DataState.Loading)

    // restaurant list
    private val _restaurantsList = mutableStateListOf<Restaurant>()
    val restaurantsList: List<Restaurant> = _restaurantsList

    // page number
    var page by mutableStateOf(1)
        private set

    // next page progress loading
    var pageLoading by mutableStateOf(false)
        private set

    // update position for check go to next page
    private var restaurantListScrollPosition = 0

    // on backPress listener
    var backState by mutableStateOf(false)

    // search state
    var isSearchIng = false
        private set

    private var lastPage = -1

    // save search query to use no internet refresh method
    private var searchRefreshQuery = ""

    // update topAppBar animation
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var lastScrollIndex = 0


    init {
        //request for restaurants list
        getRestaurantsList()

    }


    //request for search from restaurants list
    fun searchQuery(query: String) {
        isSearchIng = true
        backState = true
        newSearch(query = query)
    }

    // remove search items and show all
    suspend fun disableSearch() {
        isSearchIng = false
        resetPage()
        getRestaurantsList()
    }

    // new search request to server with page number
    private fun newSearch(query: String) {
        searchRefreshQuery = query
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                resetPage()
                uiState = LoadingSearchState.Loading
            }
            searchRestaurantUseCase(
                search = query,
                page = 1
            ).onEach { result ->
                when (result) {
                    is DataState.Loading -> {
                        uiState = LoadingSearchState.Loading
                    }
                    is DataState.Success -> {
                        lastPage = result.data.lastPage
                        _restaurantsList.addAll(result.data.data)
                        uiState = if (_restaurantsList.isEmpty()) {
                            LoadingSearchState.NotFound
                        } else {
                            LoadingSearchState.NotLoading
                        }
                    }
                    else -> {
                        lastPage = -1
                        uiState = LoadingSearchState.NotInternet
                        handleErrorsState = result
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    //reset last page
    // clear list
    private suspend fun resetPage() {
        withContext(Dispatchers.Main) {
            lastPage = -1
            restaurantListScrollPosition = 0
            page = 1
            _restaurantsList.clear()
        }
    }


    // get all restaurants list with page number
    private fun getRestaurantsList() {
        if (_restaurantsList.isEmpty() || isSearchIng) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(1000)
                getRestaurantsListUseCase(page = 1).onEach { result ->
                    when (result) {
                        is DataState.Loading -> {
                            uiState = LoadingSearchState.Loading
                        }
                        is DataState.Success -> {
                            lastPage = result.data.lastPage
                            _restaurantsList.addAll(result.data.data)
                            uiState = LoadingSearchState.NotLoading
                        }
                        else -> {
                            lastPage = -1
                            uiState = LoadingSearchState.NotInternet
                            handleErrorsState = result
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    // request  to next page
    fun onNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkLastPage()) {
                if ((restaurantListScrollPosition + 1) >= page * Constants.RESTAURANT_PAGE_SIZE) {
                    page += 1 //incrementPage
                    getRestaurantsListUseCase(page = page).onEach { result ->
                        when (result) {
                            is DataState.Loading -> {
                                pageLoading = true
                            }
                            is DataState.Success -> {
                                lastPage = result.data.lastPage
                                _restaurantsList.addAll(result.data.data)
                                pageLoading = false
                            }
                            else -> {
                                page -= 1
                                lastPage = -1
                                pageLoading = false
                                handleErrorsState = result
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    // check is not last page
    private fun checkLastPage(): Boolean {
        return lastPage > page
    }

    fun isLastPage(): Boolean {
        return lastPage == page
    }

    // update scrollUp for animated appbar
    fun updateScrollPosition(newScrollIndex: Int) {
        // check scrolling to top or bottom
        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    // update scroll position
    fun onChangeRestaurantsScrollPosition(position: Int) {
        restaurantListScrollPosition = position
    }

    fun refresh() {
        if (isSearchIng) newSearch(query = searchRefreshQuery) else getRestaurantsList()
    }

}