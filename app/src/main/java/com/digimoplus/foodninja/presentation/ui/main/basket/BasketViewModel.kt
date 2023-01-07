package com.digimoplus.foodninja.presentation.ui.main.basket

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.domain.useCase.basket.BasketUseCases
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BasketViewModel
@Inject
constructor(
    private val basketUseCases: BasketUseCases,
) : ViewModel() {

    // basket list
    private val _basketList = mutableStateListOf<Basket>()
    val basketList: List<Basket> = _basketList

    // snack bar state
    val snackBarState = SnackbarHostState()

    // total of items price
    var totalPrice by mutableStateOf(0)
        private set

    // get all basket list items
    fun getBasketList() {
        if (basketList.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                basketUseCases.getBasketListUseCase().onEach { result ->
                    when (result) {
                        is DataState.Loading -> {

                        }
                        is DataState.Success -> {
                            _basketList.addAll(result.data)
                            calculationTotalPrice()
                        }
                        else -> {
                            result.showSnackBarError(snackBarState)
                        }
                    }
                }.launchIn(viewModelScope)

            }
        }
    }

    // delete item
    fun onDelete(id: Int) {
        _basketList.removeIf { it.id == id }
        removeBasketItem(id)
        calculationTotalPrice()
    }

    // update item count form database
    fun onItemCount(id: Int, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.updateBasketItemCountUseCase(id, count)
        }
        // update count in basket list
        basketList.forEach {
            if (id == it.id)
                it.count = count
        }
        calculationTotalPrice()
    }

    //  call repository delete item
    private fun removeBasketItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            basketUseCases.deleteBasketItemUseCase(id = id)
        }
    }

    // calculation total price
    private fun calculationTotalPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            var total = 0
            basketList.forEach {
                total += (it.price.toInt() * it.count)
            }
            withContext(Dispatchers.Main) {
                totalPrice = total
            }
        }
    }
}