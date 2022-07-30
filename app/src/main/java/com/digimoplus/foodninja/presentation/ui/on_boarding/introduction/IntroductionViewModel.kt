package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.util.PreferencesKeys.introductionKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    // save user looked introduction to datastore
    suspend fun saveIntroduction() {
        dataStore.edit { preferences ->
            preferences[introductionKey] = "OK"
        }
    }

}