package com.digimoplus.foodninja.presentation.ui.menu_detail

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.model.*
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.presentation.util.UiState
import com.digimoplus.foodninja.repository.MenuDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel
@Inject
constructor(
    private val repository: MenuDetailRepository,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    var token = ""
    val loading = mutableStateOf(UiState.Loading)
    lateinit var menuInfo: MenuDetailInfo
    val commentList = mutableStateListOf<MenuDetailComments>()
    val materialsList = mutableStateListOf<String>()

    suspend fun getDetails(menuId: Int) {
        if (materialsList.size == 0) {
            getToken()
            when (val result = repository.getMenuDetails(token, menuId)) {
                is DataState.Success -> {
                    withContext(Dispatchers.Main) {
                        menuInfo = result.data.menuDetailInfo
                        materialsList.addAll(result.data.menuDetailMaterials)
                        commentList.addAll(result.data.menuDetailComments)
                        loading.value = UiState.Visible
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        loading.value = UiState.NoInternet
                    }
                }
            }
        }
    }

    private suspend fun getToken() {
        if (token == "") {
            token = dataStore.data.first()[PreferencesKeys.authenticationKey].toString()
        }
    }

}