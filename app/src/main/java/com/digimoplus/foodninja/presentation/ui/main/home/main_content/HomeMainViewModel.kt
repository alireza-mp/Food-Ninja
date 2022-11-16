package com.digimoplus.foodninja.presentation.ui.main.home.main_content

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.data.repository.HomeRepositoryImpl
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeMainViewModel
@Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    val loadingMenu = mutableStateOf(UiState.Loading)
    val restaurantList = mutableStateListOf<Restaurant>()
    val menuList = mutableStateListOf<Menu>()
    val loadingRestaurant = mutableStateOf(UiState.Loading)
    private var token = ""
    var snackBarState: SnackbarHostState? = null

    init {
        callRequests()
    }

    fun callRequests(){
        viewModelScope.launch {
            getToken()
            getMenuList()
            getRestaurantList()
        }
    }

    private fun getRestaurantList() {
        viewModelScope.launch {
            val result = repository.getRestaurantList(token)
            result.collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        loadingRestaurant.value = UiState.Loading
                    }
                    is DataState.Success -> {
                        restaurantList.addAll(dataState.data)
                        loadingRestaurant.value = UiState.Visible
                    }
                    else -> {
                        dataState.showSnackBarError(snackBarState)
                        loadingRestaurant.value = UiState.NoInternet
                    }
                }
            }
        }
    }

    private fun getMenuList() {
        viewModelScope.launch {
            val result = repository.getMenuList(token)
            result.collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        loadingMenu.value = UiState.Loading
                    }
                    is DataState.Success -> {
                        menuList.addAll(dataState.data)
                        loadingMenu.value = UiState.Visible
                    }
                    else -> {
                        dataState.showSnackBarError(snackBarState)
                        loadingMenu.value = UiState.NoInternet
                    }
                }
            }
        }
    }


    private suspend fun getToken() {
        if (token == "") {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }
        //   token =
        //     "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2U4NmNiZTZlMDUzOWE4MDBlNWQ5OTlkOGZhMGU1OWM4OTkyODk1MGJlMzI2ZGE5NmU0NTYxNmYyYzUxOWIxMTllMzEyOWIyYmQ5ZGY3OWEiLCJpYXQiOjE2NTcwMTQwNTIuNTMzNDg4LCJuYmYiOjE2NTcwMTQwNTIuNTMzNDkyLCJleHAiOjE2ODg1NTAwNTIuMzUxODc5LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.fCqwd7NHcTvzT_QUHLHweoJ-QjCEnRCRphms0O8L3cYWlNFPsMTNRS9YEg1QRfsgRI8xeAcPLtQW0ps4UW25VlntNVciVTpEn_MuV8fdu1W_rwdeAJENWAel80tUtC1lWt9uvcBR0T8t-lujVLKtB-K_mm10jDT8GtWvakiXbXO_MpHobn3oRSBd0C33VruW4a2TEeATeAMJKbfsxh7H0ZMXiUlkKMeivmAdiqHYxpjj760dx6aZHa_au8hdqDiUWjIM5oNZ68AtbjJZdOY2THvSunacsxkaoKlaFnGdRbal6CLbth41xbv7cWBGbCN-SsL2ZezxE6t-p9oaAZeeE50tnzyEvJjMhda-cpX67_jEhNfdD7A0YLJiQawGcvdvu6S-Fmcz0OUieHCia1Wiw4-bJLCDoKjNy83FbtKkDhAgic85_dAcQbNYLPnI9k39f-rudzt3X3wpcW0AHijw7uOkEJeUqZbS4RPJnVcIKoqeectu9tBcs1BGy4OOmA4tsm7-NTi9EdM06rnKieON-qyLlhpHWK5uD8-t31ZrosvV5OKCtc8YF0BdMqFpNuSXFoblb5Q54Zk54Tu-N-aZXWD2ptXJ0KRNrVd2fgrcgNr5Ww_L_G5HKP2vydfbeoOFqVh-xjkizt9pw5p28RsylACCURsgD5a8ZzKXMFK9JEI"
    }


}