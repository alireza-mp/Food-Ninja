package com.digimoplus.foodninja.presentation.ui.menu_detail

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.domain.useCase.basket.BasketUseCases
import com.digimoplus.foodninja.domain.useCase.menus.GetMenuDetailsUseCase
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel
@Inject
constructor(
    private val getMenuDetailsUseCase: GetMenuDetailsUseCase,
    private val basketUseCases: BasketUseCases,
) : ViewModel() {

    private var itemBasketId = -1L

    val snackBarHost = SnackbarHostState()

    // uiState : loading / visible / noInternet
    var uiState by mutableStateOf(UiState.Loading)
        private set

    var menuDetails by mutableStateOf<MenuDetail?>(null)
        private set

    // basket item count
    var basketCount by mutableStateOf(0)
        private set

    // alert dialog ui state
    val alertDialogVisibility = mutableStateOf(false)

    // get display details // menu info & comments list & description materials list
    fun getDetails(menuId: Int) {
        if (menuDetails == null) {
            viewModelScope.launch(Dispatchers.IO) {
                getMenuDetailsUseCase(menuId).onEach { result ->
                    when (result) {
                        is DataState.Loading -> {
                            uiState = UiState.Loading
                        }
                        is DataState.Success -> {

                            menuDetails = result.data

                            // ui state visible in this method
                            getItemCountFromDb(result.data.menuDetailInfo.id)
                        }
                        else -> {
                            uiState = UiState.NoInternet
                            result.showSnackBarError(snackBarHost)
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    // get item count form db
    private fun getItemCountFromDb(menuId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.getBasketItemCountUseCase(menuId).onEach { result ->
                when (result) {
                    is DataState.Loading -> {}
                    is DataState.Success -> {
                        result.data?.let {
                            basketCount = result.data.count
                            itemBasketId = result.data.id.toLong()
                        }
                        uiState = UiState.Visible
                    }
                    else -> {
                        uiState = UiState.NoInternet
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    // on add to cart button clicked
    fun addToCart() {
        val restaurantId = menuDetails!!.menuDetailInfo.restaurantId
        // check is current restaurant or not
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.getCurrentRestaurantUseCase(restaurantId).onEach { result ->
                when (result) {
                    is DataState.Loading -> {}
                    is DataState.Success -> {
                        if (result.data) {
                            alertDialogVisibility.value = true
                        } else {
                            addItemToBasket()
                        }
                    }
                    else -> {
                        result.showSnackBarError(snackBarHostState = snackBarHost)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    // on basket plus clicked
    fun onPlus() {
        viewModelScope.launch {
            if (basketCount != 0) {
                updateItemCount(
                    isMinus = false
                )
            } else {
                addItemToBasket()
            }
        }
    }

    // on basket minus clicked
    fun onMinus() {
        viewModelScope.launch {
            if (basketCount != 1) {
                updateItemCount(
                    isMinus = true
                )
            } else {
                basketCount = 0
                deleteBasketItem()
            }
        }
    }

    // alert dialog on yes click
    fun alertDialogOnYes() {
        val restaurantId = menuDetails!!.menuDetailInfo.restaurantId
        deleteCurrentRestaurant(restaurantId)
        addItemToBasket()
        alertDialogVisibility.value = false
    }

    // alert dialog on no click
    fun alertDialogOnNo() {
        alertDialogVisibility.value = false
    }

    // remove basket item in database
    private fun deleteBasketItem() {
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.deleteBasketItemUseCase(itemBasketId.toInt())
        }
    }

    private fun deleteCurrentRestaurant(restaurantId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.deleteCurrentRestaurantUseCase(restaurantId)
        }
    }

    // add basket item in database & update ui
    private fun addItemToBasket() {
        val menuDetailInfo = menuDetails!!.menuDetailInfo
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.addNewItemToBasketUseCase(menuDetailInfo).onEach { result ->
                when (result) {
                    is DataState.Loading -> {}
                    is DataState.Success -> {
                        itemBasketId = result.data
                        basketCount++
                    }
                    else -> {
                        result.showSnackBarError(snackBarHostState = snackBarHost)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun updateItemCount(isMinus: Boolean) {
        if (isMinus) basketCount-- else basketCount++
        val count = basketCount
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.updateBasketItemCountUseCase(itemBasketId.toInt(), count)
        }
    }

    // on retry button no intent clicked
    fun refresh(menuId: Int) {
        getDetails(menuId)
    }


}