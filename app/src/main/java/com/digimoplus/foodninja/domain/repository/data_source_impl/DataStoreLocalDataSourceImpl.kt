package com.digimoplus.foodninja.domain.repository.data_source_impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreLocalDataSourceImpl
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStoreLocalDataSource {

    override suspend fun getToken(): String =
        dataStore.data.first()[PreferencesKeys.authenticationKey].toString()

    override suspend fun getUserId(): Int =
        dataStore.data.first()[PreferencesKeys.userId]?.toInt() ?: 0

    override suspend fun getCompleteRegister(): String =
        dataStore.data.first()[PreferencesKeys.completeRegisterKey].toString()

    override suspend fun getUserIntroduction(): String =
        dataStore.data.first()[PreferencesKeys.introductionKey].toString()

    override suspend fun getUserProfileUrl(): String =
        dataStore.data.first()[PreferencesKeys.userProfileUrl].toString()

    override suspend fun getUserPaymentMethod(): String =
        dataStore.data.first()[PreferencesKeys.userPaymentMethod].toString()


    override suspend fun saveUserId(id: Int?) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userId] = id ?: 0
        }
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.authenticationKey] = "Bearer $token"
        }
    }

    override suspend fun saveCompleteRegister(state: Boolean) {
        if (state) {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.completeRegisterKey] = "OK"
            }
        }
    }

    override suspend fun saveUserProfileUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userProfileUrl] = url
        }
    }

    override suspend fun saveUserPaymentMethod(payment: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userPaymentMethod] = payment
        }
    }

    override suspend fun saveIntroduction(state: Boolean) {
        if (state) {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.introductionKey] = "OK"
            }
        }
    }
}