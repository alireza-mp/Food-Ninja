package com.digimoplus.foodninja.presentation.ui.main

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository,
) : ViewModel() {

    // basket badge
    val basketBadge = mutableStateOf(0)

    // chat badge
    val chatBadge = mutableStateOf(0)

    // snack bar state
    val snackBarState = SnackbarHostState()

    //bottom navigation visibility
    val showBottomTab = mutableStateOf(true)

    // check basket table items & update basket badge in bottom navigation bar
    fun updateBasketBadge() {
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.checkBasketItemsCount()
            withContext(Dispatchers.Main) {
                basketBadge.value = count
            }
        }
    }

}