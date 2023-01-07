package com.digimoplus.foodninja.presentation.ui.main.home.home_detail_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.useCase.home_detail.HomeDetailUseCase
import com.digimoplus.foodninja.domain.util.DataState
import com.digimoplus.foodninja.domain.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeDetailViewModel
@Inject constructor(
    private val homeDetailUseCase: HomeDetailUseCase,
) : ViewModel() {

    var handleErrorsState by mutableStateOf<DataState<Any>>(DataState.Loading)

    var uiState by mutableStateOf(UiState.Loading)
        private set

    private val _restaurantList = mutableStateListOf<Restaurant>()
    val restaurantList: List<Restaurant> = _restaurantList

    private val _menuList = mutableStateListOf<Menu>()
    val menuList: List<Menu> = _menuList

    init {
        initialData()
    }

    private fun initialData() {
        viewModelScope.launch(Dispatchers.IO) {
            homeDetailUseCase().onEach { result ->
                uiState = when (result) {
                    is DataState.Loading -> {
                        UiState.Loading
                    }
                    is DataState.Success -> {
                        _restaurantList.addAll(result.data.restaurantList)
                        _menuList.addAll(result.data.menuList)
                        UiState.Visible
                    }
                    else -> {
                        handleErrorsState = result
                        UiState.NoInternet
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun refresh() {
        initialData()
    }
}