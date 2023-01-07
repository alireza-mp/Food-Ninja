package com.digimoplus.foodninja.domain.util

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    // flag for check show introduction to user
    val introductionKey = stringPreferencesKey("INTRODUCTION_KEY")

    // flag for check user is authenticated
    val authenticationKey = stringPreferencesKey("AUTHENTICATION_KEY")

    // flag for check user register data is completed
    val completeRegisterKey = stringPreferencesKey("COMPLETE_REGISTER_KEY")

    // user id
    val userId = intPreferencesKey("ID_KEY")

    // user payment method
    val userPaymentMethod = stringPreferencesKey("PAYMENT_KEY")

    // user profile image url
    val userProfileUrl = stringPreferencesKey("PROFILE_URL_KEY")
}