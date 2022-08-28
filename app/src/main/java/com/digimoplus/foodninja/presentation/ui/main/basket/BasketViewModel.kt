package com.digimoplus.foodninja.presentation.ui.main.basket

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.util.showSnackBarError
import com.digimoplus.foodninja.domain.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BasketViewModel
@Inject
constructor(
    private val repository: BasketRepository,
) : ViewModel() {

    // basket list
    val basketList = mutableStateListOf<Basket>()

    // snack bar state
    val snackBarState = SnackbarHostState()

    // total of items price
    val totalPrice = mutableStateOf(0)

    // get all basket list items
    suspend fun getBasketList() {
        if (basketList.isEmpty()) {
            withContext(Dispatchers.IO) {
                val result = repository.basketList()
                withContext(Dispatchers.Main) {
                    when (result) {
                        is DataState.Success -> {
                            withContext(Dispatchers.Main) {
                                basketList.addAll(result.data)
                                calculationTotalPrice()
                            }
                        }
                        else -> {
                            result.showSnackBarError(snackBarState)
                        }
                    }
                }
            }
        }
    }

    // delete item
    fun onDelete(id: Int) {
        basketList.removeIf { it.id == id }
        removeBasketItem(id)
        calculationTotalPrice()
    }

    // update item count form database
    fun onItemCount(id: Int, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCount(id, count)
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
            repository.deleteBasketItem(id = id)
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
                totalPrice.value = total
            }
        }
    }
}