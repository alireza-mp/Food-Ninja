package com.digimoplus.foodninja.domain.util

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    // check user is first time
    val introductionKey = stringPreferencesKey("INTRODUCTION_KEY")

    // check user is registered
    val authenticationKey = stringPreferencesKey("AUTHENTICATION_KEY")

    // check user information is completed
    val userInformation = stringPreferencesKey("INFORMATION_KEY")

    // user id
    val userId = intPreferencesKey("ID_KEY")
}