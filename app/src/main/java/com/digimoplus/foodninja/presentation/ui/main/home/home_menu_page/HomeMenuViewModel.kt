package com.digimoplus.foodninja.presentation.ui.main.home.menu_content

import androidx.compose.material.SnackbarHostState
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
import com.digimoplus.foodninja.domain.util.showSnackBarError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    // snack bar state
    var snackBarHostState: SnackbarHostState? = null

    // ui state / loading / not loading / not found
    var uiState by mutableStateOf(LoadingSearchState.Loading)
        private set

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
    var searchIng = false
        private set

    // update topAppBar animation
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var lastScrollIndex = 0

    // get token
    // request for get menus list with page number
    init {
        getMenusList()
    }

    //request for search from restaurants list
    fun searchQuery(query: String) {
        searchIng = true
        backState.value = true
        newSearch(query = query)
    }

    // remove search items and show all
    fun resetList() {
        searchIng = false
        backState.value = false
        resetPage()
        getMenusList()
    }

    // new search request to server with page number
    private fun newSearch(query: String) {
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
                        result.showSnackBarError(snackBarHostState)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun resetPage() {
        viewModelScope.launch(Dispatchers.Main) {
            lastPage = -1
            restaurantListScrollPosition = 0
            page = 1
            _menuList.clear()
        }
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
        if (_menuList.isEmpty() || searchIng) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(1000)
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
                            result.showSnackBarError(snackBarHostState)
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
                    delay(1000)
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
                                result.showSnackBarError(snackBarHostState)
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

}