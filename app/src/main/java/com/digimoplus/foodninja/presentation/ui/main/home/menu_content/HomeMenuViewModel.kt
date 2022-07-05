package com.digimoplus.foodninja.presentation.ui.main.home.menu_content

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
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.presentation.util.LoadingSearchState
import com.digimoplus.foodninja.presentation.util.showSnackBarError
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import com.digimoplus.foodninja.repository.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeMenuViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    var snackBarHostState: SnackbarHostState? = null
    val loadingMenu = mutableStateOf(LoadingSearchState.Loading)
    val pageLoading = mutableStateOf(false)
    val menuAllList = mutableStateListOf<Menu>()
    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> get() = _scrollUp
    private var restaurantListScrollPosition = 0
    val page = mutableStateOf(1)
    private var token = ""
    var listAnim = true
    private var lastScrollIndex = 0
    val backState = mutableStateOf(false)
    var searchIng = false
    val a = false


    init {
        viewModelScope.launch(Dispatchers.Main) {
            getToken()
            getAllMenuList()
        }
    }

    fun searchQuery(query: String) {
        searchIng = true
        backState.value = true
        newSearch(query = query)
    }

    suspend fun resetList() {
        resetPage()
        getAllMenuList()
    }

    private fun newSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPage()
            loadingMenu.value = LoadingSearchState.Loading
            when (val result = repository.menuSearch(
                token = token,
                search = query,
                page = 1
            )) {
                is DataState.Success -> {
                    menuAllList.addAll(result.data)
                    if (menuAllList.size == 0) {
                        loadingMenu.value = LoadingSearchState.NotFound
                    } else {
                        loadingMenu.value = LoadingSearchState.NotLoading
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

    private suspend fun getAllMenuList() {
        if (menuAllList.size == 0 || searchIng) {
            loadingMenu.value = LoadingSearchState.Loading
            withContext(Dispatchers.IO) {
                delay(1000)
                when (val result = repository.getAllMenuList(token, page = 1)) {
                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            menuAllList.addAll(result.data)
                            loadingMenu.value = LoadingSearchState.NotLoading
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

    fun onNextPage() {
        viewModelScope.launch(Dispatchers.IO) {

            if (repository.lastPage > page.value) {
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

    fun onChangeMenuScrollPosition(position: Int) {
        restaurantListScrollPosition = position
    }

    fun checkIsLastPage(): Boolean {
        Log.d(TAG, "checkIsLastPage: repo : ${repository.lastPage} /// page : ${page.value}")
        return repository.lastPage == page.value
    }

}