package com.digimoplus.foodninja.presentation.ui.main.home.main_content

import android.util.Log
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
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.presentation.util.showSnackBarError
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeMainViewModel
@Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    val loadingMenu = mutableStateOf(true)
    val restaurantList = mutableStateListOf<Restaurant>()
    val menuList = mutableStateListOf<Menu>()
    val loadingRestaurant = mutableStateOf(true)
    private var token = ""
    var snackBarState: SnackbarHostState? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getToken()
            getRestaurantList()
            getMenuList()
        }
    }


    private suspend fun getRestaurantList() {
        delay(250)
        when (val result = repository.getRestaurantList(token)) {
            is DataState.Success -> {
                restaurantList.addAll(result.data)
                loadingRestaurant.value = false
            }
            else -> {
                result.showSnackBarError(snackBarState)
            }
        }
    }

    private suspend fun getMenuList() {
        delay(250)
        when (val result = repository.getMenuList(token)) {
            is DataState.Success -> {
                menuList.addAll(result.data)
                loadingMenu.value = false
            }
            else -> {
                result.showSnackBarError(snackBarState)
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