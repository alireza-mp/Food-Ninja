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

    // user payment method
    val userPaymentMethod = stringPreferencesKey("PAYMENT_KEY")

    // user profile image url
    val userProfileUrl = stringPreferencesKey("PROFILE_URL_KEY")
}