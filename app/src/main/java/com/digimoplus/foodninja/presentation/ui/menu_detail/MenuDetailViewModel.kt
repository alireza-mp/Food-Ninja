package com.digimoplus.foodninja.presentation.ui.menu_detail

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.MenuDetailComments
import com.digimoplus.foodninja.domain.model.MenuDetailInfo
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

    // token
    var token = ""

    // user id
    var userId: Int = 0

    // uiState : loading / visible / noInternet
    val uiState = mutableStateOf(UiState.Loading)

    // menu details info
    lateinit var menuInfo: MenuDetailInfo

    // comment list
    val commentList = mutableStateListOf<MenuDetailComments>()

    //list material items in description text
    val materialsList = mutableStateListOf<String>()

    // basket item count
    val basketCount = mutableStateOf(0)

    // alert dialog ui state
    val alertDialogVisibility = mutableStateOf(false)


    // get display details // menu info & comments list & description materials list
    suspend fun getDetails(menuId: Int) {
        if (materialsList.size == 0) {
            getToken()
            when (val result = repository.getMenuDetails(token, menuId)) {
                is DataState.Success -> {
                    withContext(Dispatchers.Main) {
                        menuInfo = result.data.menuDetailInfo
                        materialsList.addAll(result.data.menuDetailMaterials)
                        commentList.addAll(result.data.menuDetailComments)
                        uiState.value = UiState.Visible
                    }
                    getUserId()
                    // check if menu exist in basket and get count
                    checkIsExist(userId = userId, menuId = menuInfo.id)
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        uiState.value = UiState.NoInternet
                    }
                }
            }
        }
    }

    // on add to cart button clicked
    fun firstPlus() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkRestaurants(menuInfo.restaurantId)) {
                alertDialogVisibility.value = true
            } else {
                onPlus()
            }
        }
    }

    // on basket plus clicked
    fun onPlus() {
        viewModelScope.launch {
            addToBasket(
                isMinus = false,
                count = basketCount.value + 1
            )
        }
    }

    // on basket minus clicked
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

    // alert dialog on yes click
    fun alertDialogOnYes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeOtherRestaurants(menuInfo.restaurantId)
            onPlus()
        }
        alertDialogVisibility.value = false
    }

    // alert dialog on no click
    fun alertDialogOnNo() {
        alertDialogVisibility.value = false
    }

    // remove basket item in database
    private fun deleteBasketItem() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBasketItem(userId, menuInfo.id)
        }
    }

    // add basket item in database & update ui
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
                //show snack bar to error
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

    // get token from datastore
    private suspend fun getToken() {
        if (token == "") {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }
    }

    // get user id from datastore
    private suspend fun getUserId() {
        if (userId == 0) {
            userId = dataStore.data.first()[PreferencesKeys.userId]?.toInt() ?: 0
        }
    }

    // on retry button no intent clicked
    fun noInternetConnection(menuId: Int) {
        uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getDetails(menuId)
        }
    }


}