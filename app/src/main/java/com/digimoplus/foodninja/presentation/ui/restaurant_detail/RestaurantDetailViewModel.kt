package com.digimoplus.foodninja.presentation.ui.restaurant_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.RestaurantDetail
import com.digimoplus.foodninja.domain.useCase.restaurants.GetRestaurantDetailsUseCase
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel
@Inject
constructor(
    private val getRestaurantDetailsUseCase: GetRestaurantDetailsUseCase,
) : ViewModel() {

    // ui state -> loading / visible / noIntent
    var uiState by mutableStateOf(UiState.Loading)
        private set

    // restaurant info
    var restaurantDetails = mutableStateOf<RestaurantDetail?>(null)

    fun getDetails(restaurantId: Int) {
        if (restaurantDetails.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                getRestaurantDetailsUseCase(restaurantId).onEach { result ->
                    when (result) {
                        is DataState.Loading -> {
                            uiState = UiState.Loading
                        }
                        is DataState.Success -> {
                            restaurantDetails.value = result.data
                            uiState = UiState.Visible
                        }
                        else -> {
                            uiState = UiState.NoInternet
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun refresh(restaurantId: Int) {
        getDetails(restaurantId)
    }
}