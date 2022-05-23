package com.digimoplus.foodninja.presentation.ui.main.home.menu_content

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel

@Composable
fun MenuContent(
    homeViewModel: HomeViewModel,
    snackBarHostState: SnackbarHostState
) {

    val viewModel: HomeMenuViewModel = viewModel()
    viewModel.snackBarHostState = snackBarHostState
    //homeViewModel.search

}