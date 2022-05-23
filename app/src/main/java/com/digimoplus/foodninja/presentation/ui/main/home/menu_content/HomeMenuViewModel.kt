package com.digimoplus.foodninja.presentation.ui.main.home.menu_content

import androidx.compose.material.SnackbarHostState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeMenuViewModel
@Inject
constructor(
    private val repository: HomeRepositoryImpl,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    var snackBarHostState: SnackbarHostState? = null

}