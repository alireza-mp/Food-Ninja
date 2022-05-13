package com.digimoplus.foodninja.presentation.ui.home

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.presentation.components.util.showSnackBarError
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    private var token = ""
    val search = mutableStateOf("")
    val restaurantList = mutableStateListOf<Restaurant>()
    val menuList = mutableStateListOf<Menu>()
    val snackBarState = SnackbarHostState()
    val loadingRestaurant = mutableStateOf(true)
    val loadingMenu = mutableStateOf(true)

    init {
        getToken()
        getRestaurantList()
        getMenuList()
    }

    private fun getRestaurantList() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(250)
            when (val result = repository.getRestaurantList(token)) {
                is DataState.Success -> {
                    restaurantList.addAll(result.data)
                }
                else -> {
                    result.showSnackBarError(snackBarState)
                }
            }
            loadingRestaurant.value = false
        }
    }

    private fun getMenuList() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(250)
            when (val result = repository.getMenuList(token)) {
                is DataState.Success -> {
                    menuList.addAll(result.data)
                }
                else -> {
                    result.showSnackBarError(snackBarState)
                }
            }
            loadingMenu.value = false
        }
    }

    private fun getToken() {
        /*viewModelScope.launch(Dispatchers.IO) {
              token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }*/
        token =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZGZkMjY3NDhiZDc1ODI3ZGQ4OTE1MWIzNzg2NmNhZTczM2VkOTBkOGVhYmMxODdiMWRiZDYzY2NmMmRjMjVlZDIzM2ZmYWE5M2U3ZjFiODYiLCJpYXQiOjE2NTE2ODU5NDkuNDAyNTk5LCJuYmYiOjE2NTE2ODU5NDkuNDAyNjAzLCJleHAiOjE2ODMyMjE5NDkuMjAyMjUzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.EsXpFoEhuVMcdb6lbFrsl35wu6KcmQpI1BNhWRvE9pX98CtDP6dZEzYX6FNqSs8BoZbZ8uqv-7hOWU1rLqG5rDvFvcT9aikuzENylbhDJQDUnU5UeAoY8QnNQv2AnX5wGXd5ceG7aiLI3eOu9Y5nluq7vY1ONY_ZrKDyj9-GYs01l_vyLm2EyaGsiK41Z-o8lJD70d9qoGiCV1GqalQkGAO3HhPe6PC9J6dtuLBY67SdNvGGkFbzjYtryNY_oXHI_Hg0UimXfG9GpPc-x2IMhoeO6o0nWy9qaIn7g-G_KCrb7NIkYnKaX7AfxbOUPEXHKNk3HkXJ2S-pIywAYpGkmMs9XU3TtPdRjqozeA1bFU6wKih6ZS8cHuGrqmn1yBfznGM3RxQ5t9v2PipR_VXq4GYEqKrBECUz472jroluawIUrhpkVmpbDYMITQf8mIiXI32Vup9ZKtesY2eWunvZ12kcxD-CPuptoao9XPBSXW7s2DV8-oz-A9rtwz3HX1hJeRtPll1FGhrJTBCZVAhVXNHKP0EKk43omO6jTxpAPZG7mJvMVceX-6Vmbb9UClGKH39aeS6ZhBN0i54-t4WwzKy8hKtvIcIHsK6Iq5BgNXJGu6ecGNdq13mU4HGRUm14_fa-xt64_drjhUN2NUbybSF8dJEAgLslt50ojqR2LF4"
    }

}