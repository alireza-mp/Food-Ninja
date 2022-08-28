package com.digimoplus.foodninja.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.repository.RegisterUserRepository
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.data.api.AuthService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RegisterUserRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
) : RegisterUserRepository {

    // save name & family & phone & location to server
    override suspend fun addUserInformation(
        name: String,
        family: String,
        phone: String,
        location: String,
    ): Register {
        try {
            val id = dataStore.data.first()[PreferencesKeys.userId] ?: 0
            val response = authService.addUserInfo(id, name, family, phone, location)
            return when (response.code()) {
                422 -> {
                    Register.WrongError
                }
                200 -> {
                    saveInformationKey()
                    Register.Successful
                }
                else -> {
                    Register.SomeError
                }
            }
        } catch (e: Exception) {
            return Register.NetworkError
        }
    }

    // save information state to datastore
    private suspend fun saveInformationKey() {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userInformation] = "OK"
        }
    }
}