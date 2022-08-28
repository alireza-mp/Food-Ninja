package com.digimoplus.foodninja.presentation.ui.main.home.menu_content

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
import com.digimoplus.foodninja.domain.model.Menu
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
class HomeMenuViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {
    // snack bar state
    var snackBarHostState: SnackbarHostState? = null

    // ui state / loading / not loading / not found
    val uiState = mutableStateOf(LoadingSearchState.Loading)

    // next page loading progress
    val pageLoading = mutableStateOf(false)

    // menus list
    val menuAllList = mutableStateListOf<Menu>()

    // list position for check next page
    private var restaurantListScrollPosition = 0

    // page number
    val page = mutableStateOf(1)

    // token
    private var token = ""

    // animate items state
    var listAnim = true

    // backPress listener state
    val backState = mutableStateOf(false)

    // search state
    var searchIng = false

    // update topAppBar animation
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var lastScrollIndex = 0

    // get token
    // request for get menus list with page number
    init {
        viewModelScope.launch(Dispatchers.Main) {
            getToken()
            getAllMenuList()
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
        getAllMenuList()
    }

    // new search request to server with page number
    private fun newSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPage()
            uiState.value = LoadingSearchState.Loading
            when (val result = repository.menuSearch(
                token = token,
                search = query,
                page = 1
            )) {
                is DataState.Success -> {
                    menuAllList.addAll(result.data)
                    if (menuAllList.size == 0) {
                        uiState.value = LoadingSearchState.NotFound
                    } else {
                        uiState.value = LoadingSearchState.NotLoading
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        result.showSnackBarError(snackBarHostState)
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
            menuAllList.clear()
        }
    }

    // update scrollUp for animated appbar
    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return
        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    // get token
    private suspend fun getToken() {
        withContext(Dispatchers.IO) {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()

        }
    }

    // get all menus list with page
    private suspend fun getAllMenuList() {
        // if menu list is empty || search  state is ture
        if (menuAllList.size == 0 || searchIng) {
            uiState.value = LoadingSearchState.Loading
            withContext(Dispatchers.IO) {
                delay(1000)
                when (val result = repository.getAllMenuList(token, page = 1)) {
                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            menuAllList.addAll(result.data)
                            uiState.value = LoadingSearchState.NotLoading
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            result.showSnackBarError(snackBarHostState)
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

                    when (val result = repository.getAllMenuList(token, page = page.value)) {
                        is DataState.Success -> {
                            menuAllList.addAll(result.data)
                            pageLoading.value = false
                        }
                        else -> {
                            page.value = page.value - 1
                            pageLoading.value = false
                            result.showSnackBarError(snackBarHostState)
                        }
                    }
                }
            } else {
                page.value = page.value + 1 //incrementPage
            }
        }
    }

    // update scroll position
    fun onChangeMenuScrollPosition(position: Int) {
        restaurantListScrollPosition = position
    }

    // is last page
    private fun checkIsLastPage(): Boolean {
        return repository.lastPage > page.value
    }

}