package com.digimoplus.foodninja.presentation.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor() : ViewModel() {

    val basketBadge = mutableStateOf(7)
    val chatBadge = mutableStateOf(0)


}