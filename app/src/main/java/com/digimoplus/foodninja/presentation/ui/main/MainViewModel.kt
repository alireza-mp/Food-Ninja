package com.digimoplus.foodninja.presentation.ui.main

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digimoplus.foodninja.repository.MainRepository
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

    val basketBadge = mutableStateOf(0)
    val chatBadge = mutableStateOf(0)
    val snackBarState = SnackbarHostState()
    val showBottomTab = mutableStateOf(true)

    fun updateBasketBadge() {
        viewModelScope.launch(Dispatchers.IO) {
            val count = repository.checkBasketItemsCount()
            withContext(Dispatchers.Main) {
                basketBadge.value = count
            }
        }
    }

}