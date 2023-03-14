package com.digimoplus.foodninja.presentation.ui.main.home.home_menu_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.useCase.menus.GetMenusListUseCase
import com.digimoplus.foodninja.domain.useCase.menus.SearchMenuUseCase
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.LoadingSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMenuViewModel
@Inject
constructor(
    private val getMenusListUseCase: GetMenusListUseCase,
    private val searchMenuUseCase: SearchMenuUseCase,
) : ViewModel() {


    // ui state / loading / not loading / not found
    var uiState by mutableStateOf(LoadingSearchState.Loading)
        private set

    var handleErrorsState by mutableStateOf<DataState<Any>>(DataState.Loading)

    // next page loading progress
    var pageLoading by mutableStateOf(false)
        private set

    // menus list
    private val _menuList = mutableStateListOf<Menu>()
    val menuList: List<Menu> = _menuList

    // list position for check next page
    private var restaurantListScrollPosition = 0

    // page number
    var page = 1
        private set

    private var lastPage = -1

    // animate items state
    var listAnim = true

    // backPress listener state
    val backState = mutableStateOf(false)

    // search state
    var isSearchIng = false
        private set

    // save search query to use no internet refresh method
    private var searchRefreshQuery = ""

    // update topAppBar animation
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var lastScrollIndex = 0

    // request for get menus list with page number
    init {
        getMenusList()
    }

    //request for search from restaurants list
    fun searchQuery(query: String) {
        isSearchIng = true
        backState.value = true
        newSearch(query = query)
    }

    // remove search items and show all
    fun resetList() {
        viewModelScope.launch {
            isSearchIng = false
            backState.value = false
            resetPage()
            getMenusList()
        }
    }

    // new search request to server with page number
    private fun newSearch(query: String) {
        searchRefreshQuery = query
        viewModelScope.launch(Dispatchers.IO) {
            resetPage()
            searchMenuUseCase(
                page = page,
                search = query,
            ).onEach { result ->
                when (result) {
                    is DataState.Loading -> {
                        uiState = LoadingSearchState.Loading
                    }
                    is DataState.Success -> {
                        lastPage = result.data.lastPage
                        _menuList.addAll(result.data.data)
                        uiState = if (_menuList.isEmpty()) {
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

    private fun resetPage() {
        lastPage = -1
        restaurantListScrollPosition = 0
        page = 1
        _menuList.clear()
    }

    // update scrollUp for animated appbar
    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return
        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    // get all menus list with page
    private fun getMenusList() {
        // if menu list is empty || search  state is ture
        if (_menuList.isEmpty() || isSearchIng) {
            viewModelScope.launch(Dispatchers.IO) {
                getMenusListUseCase(page = 1).onEach { result ->
                    when (result) {
                        is DataState.Loading -> {
                            uiState = LoadingSearchState.Loading
                        }
                        is DataState.Success -> {
                            lastPage = result.data.lastPage
                            _menuList.addAll(result.data.data)
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
            if (checkIsLastPage()) {
                if ((restaurantListScrollPosition + 1) >= page * Constants.MENU_PAGE_SIZE) {
                    page += 1 //incrementPage
                    getMenusListUseCase(page = page).onEach { result ->
                        when (result) {
                            is DataState.Loading -> {
                                pageLoading = true
                            }
                            is DataState.Success -> {
                                lastPage = result.data.lastPage
                                _menuList.addAll(result.data.data)
                                pageLoading = false
                            }
                            else -> {
                                lastPage = -1
                                page -= 1
                                pageLoading = false
                                handleErrorsState = result
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    // update scroll position
    fun onChangeMenuScrollPosition(position: Int) {
        restaurantListScrollPosition = position
    }

    // is last page
    private fun checkIsLastPage(): Boolean {
        return lastPage > page
    }

    fun refresh() {
        if (isSearchIng) newSearch(searchRefreshQuery) else getMenusList()
    }

    fun isLastPage(): Boolean {
        return lastPage == page
    }

}