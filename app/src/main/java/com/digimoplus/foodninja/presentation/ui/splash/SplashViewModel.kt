package com.digimoplus.foodninja.presentation.ui.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.util.OnlineChecker
import com.digimoplus.foodninja.domain.util.PreferencesKeys.authenticationKey
import com.digimoplus.foodninja.domain.util.PreferencesKeys.introductionKey
import com.digimoplus.foodninja.domain.util.PreferencesKeys.userInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
    private val onlineChecker: OnlineChecker,
) : ViewModel() {

    suspend fun isOnline(): Boolean {
        return withContext(Dispatchers.IO) {
            onlineChecker.isOnline
        }
    }

    val checkIntroduction: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[introductionKey] ?: "null"
        }

    // check user authentication
    val checkAuthentication: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[authenticationKey] ?: "null"
        }

    // check is user information is completed or not
    val checkUserInformation: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[userInformation] ?: "null"
        }
}