package com.digimoplus.foodninja.presentation.ui.menu_detail

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.*
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.presentation.util.UiState
import com.digimoplus.foodninja.repository.MenuDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel
@Inject
constructor(
    private val repository: MenuDetailRepository,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    var token = ""
    var userId: Int = 0
    val loading = mutableStateOf(UiState.Loading)
    lateinit var menuInfo: MenuDetailInfo
    val commentList = mutableStateListOf<MenuDetailComments>()
    val materialsList = mutableStateListOf<String>()
    val basketCount = mutableStateOf(0)

    suspend fun getDetails(menuId: Int) {
        if (materialsList.size == 0) {
            getToken()
            when (val result = repository.getMenuDetails(token, menuId)) {
                is DataState.Success -> {
                    withContext(Dispatchers.Main) {
                        menuInfo = result.data.menuDetailInfo
                        materialsList.addAll(result.data.menuDetailMaterials)
                        commentList.addAll(result.data.menuDetailComments)
                        loading.value = UiState.Visible
                    }
                    getUserId()
                    // check if menu exist in basket and get count
                    checkIsExist(userId = userId, menuId = menuInfo.id)
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        loading.value = UiState.NoInternet
                    }
                }
            }
        }
    }


    fun onPlus() {
        viewModelScope.launch {
            addToBasket(
                isMinus = false,
                count = basketCount.value + 1
            )
        }
    }

    fun onMinus() {
        viewModelScope.launch {
            if (basketCount.value != 1)
                addToBasket(
                    isMinus = true,
                    count = basketCount.value - 1
                )
            else {
                basketCount.value = 0
                deleteBasketItem()
            }

        }
    }

    private fun deleteBasketItem() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBasketItem(userId, menuInfo.id)
        }
    }

    private suspend fun addToBasket(isMinus: Boolean, count: Int) {
        getUserId()
        val result = repository.addToBasket(
            menuDetailInfo = menuInfo,
            userId = userId,
            count = count
        )
        when (result) {
            is DataState.SuccessMessage -> {
                if (isMinus) {
                    basketCount.value = basketCount.value - 1
                } else {
                    basketCount.value = basketCount.value + 1
                }
            }
            else -> {
                //snack bar
            }
        }
    }

    // check if menu exist in basket & get count
    private suspend fun checkIsExist(userId: Int, menuId: Int) {
        val count = repository.checkIsExist(userId = userId, menuId = menuId)
        withContext(Dispatchers.Main) {
            basketCount.value = count
        }
    }

    private suspend fun getToken() {
        if (token == "") {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }
    }

    private suspend fun getUserId() {
        if (userId == 0) {
            userId = dataStore.data.first()[PreferencesKeys.userId]?.toInt() ?: 0
        }
    }


}