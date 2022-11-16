package com.digimoplus.foodninja.presentation.ui.restaurant_detail

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.model.RestoDetailComment
import com.digimoplus.foodninja.domain.model.RestoDetailInfo
import com.digimoplus.foodninja.domain.model.RestoDetailMenu
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.domain.repository.RestoDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel
@Inject
constructor(
    private val repository: RestoDetailRepository,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    // token
    var token = ""

    // ui state : loading / visible / noIntent
    val uiState = mutableStateOf(UiState.Loading)

    // resto info
    lateinit var restoInfo: RestoDetailInfo

    // comments list
    val commentList = mutableStateListOf<RestoDetailComment>()

    // menu lists
    val menuList = mutableStateListOf<RestoDetailMenu>()

    // get details from server // resto info & commentsList & MenusList
    suspend fun getDetails(restaurantId: Int) {
        if (menuList.size == 0) {
            getToken()
            when (val result = repository.getRestaurantDetails(token, restaurantId)) {
                is DataState.Success -> {
                    withContext(Dispatchers.Main) {
                        restoInfo = result.data.restoDetailInfo
                        menuList.addAll(result.data.restoDetailMenus)
                        commentList.addAll(result.data.restoDetailComment)
                        // update ui
                        uiState.value = UiState.Visible
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        // update ui
                        uiState.value = UiState.NoInternet
                    }
                }
            }
        }
    }

    // get token
    private suspend fun getToken() {
        if (token == "") {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }
    }

}