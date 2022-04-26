package com.digimoplus.foodninja.presentation.ui.splash

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.OnlineChecker
import com.digimoplus.foodninja.domain.util.PreferencesKeys.authenticationKey
import com.digimoplus.foodninja.domain.util.PreferencesKeys.introductionKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
    private val onlineChecker: OnlineChecker
) : ViewModel() {

    fun isOnline(): Boolean {
        return onlineChecker.isOnline
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
}