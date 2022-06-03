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
    var listAnim = true
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
        //   token =
        //]     "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZGZkMjY3NDhiZDc1ODI3ZGQ4OTE1MWIzNzg2NmNhZTczM2VkOTBkOGVhYmMxODdiMWRiZDYzY2NmMmRjMjVlZDIzM2ZmYWE5M2U3ZjFiODYiLCJpYXQiOjE2NTE2ODU5NDkuNDAyNTk5LCJuYmYiOjE2NTE2ODU5NDkuNDAyNjAzLCJleHAiOjE2ODMyMjE5NDkuMjAyMjUzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.EsXpFoEhuVMcdb6lbFrsl35wu6KcmQpI1BNhWRvE9pX98CtDP6dZEzYX6FNqSs8BoZbZ8uqv-7hOWU1rLqG5rDvFvcT9aikuzENylbhDJQDUnU5UeAoY8QnNQv2AnX5wGXd5ceG7aiLI3eOu9Y5nluq7vY1ONY_ZrKDyj9-GYs01l_vyLm2EyaGsiK41Z-o8lJD70d9qoGiCV1GqalQkGAO3HhPe6PC9J6dtuLBY67SdNvGGkFbzjYtryNY_oXHI_Hg0UimXfG9GpPc-x2IMhoeO6o0nWy9qaIn7g-G_KCrb7NIkYnKaX7AfxbOUPEXHKNk3HkXJ2S-pIywAYpGkmMs9XU3TtPdRjqozeA1bFU6wKih6ZS8cHuGrqmn1yBfznGM3RxQ5t9v2PipR_VXq4GYEqKrBECUz472jroluawIUrhpkVmpbDYMITQf8mIiXI32Vup9ZKtesY2eWunvZ12kcxD-CPuptoao9XPBSXW7s2DV8-oz-A9rtwz3HX1hJeRtPll1FGhrJTBCZVAhVXNHKP0EKk43omO6jTxpAPZG7mJvMVceX-6Vmbb9UClGKH39aeS6ZhBN0i54-t4WwzKy8hKtvIcIHsK6Iq5BgNXJGu6ecGNdq13mU4HGRUm14_fa-xt64_drjhUN2NUbybSF8dJEAgLslt50ojqR2LF4"
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