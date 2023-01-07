package com.digimoplus.foodninja.presentation.ui.main

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.useCase.basket.GetBasketItemsCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val getBasketItemsCountUseCase: GetBasketItemsCountUseCase,
) : ViewModel() {

    // basket badge
    var basketBadge by mutableStateOf(0)
        private set

    // chat badge
    var chatBadge by mutableStateOf(0)
        private set

    // snack bar state
    val snackBarState = SnackbarHostState()

    //bottom navigation visibility
    val showBottomTab = mutableStateOf(true)

    // check basket table items & update basket badge in bottom navigation bar
    fun updateBasketBadge() {
        viewModelScope.launch(Dispatchers.IO) {
            val count = getBasketItemsCountUseCase()
            withContext(Dispatchers.Main) {
                basketBadge = count
            }
        }
    }

}