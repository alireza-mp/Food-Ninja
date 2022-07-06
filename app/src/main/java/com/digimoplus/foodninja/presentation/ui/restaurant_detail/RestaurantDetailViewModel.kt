package com.digimoplus.foodninja.presentation.ui.restaurant_detail

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.RestoDetailComment
import com.digimoplus.foodninja.domain.model.RestoDetailInfo
import com.digimoplus.foodninja.domain.model.RestoDetailMenu
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.repository.RestoDetailRepository
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

    var token = ""
    val loading = mutableStateOf("true")
    lateinit var restoInfo: RestoDetailInfo
    val commentList = mutableStateListOf<RestoDetailComment>()
    val menuList = mutableStateListOf<RestoDetailMenu>()

    suspend fun getDetails(restaurantId: Int) {
        if (menuList.size == 0) {
            getToken()
            when (val result = repository.getDetails(token, restaurantId)) {
                is DataState.Success -> {
                    withContext(Dispatchers.Main) {
                        restoInfo = result.data.restoDetailInfo
                        menuList.addAll(result.data.restoDetailMenus)
                        commentList.addAll(result.data.restoDetailComment)
                        loading.value = "false"
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        loading.value = "no_internet"
                    }
                }
            }
        }
    }

    private suspend fun getToken() {
        if (token == "") {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }
    }

}