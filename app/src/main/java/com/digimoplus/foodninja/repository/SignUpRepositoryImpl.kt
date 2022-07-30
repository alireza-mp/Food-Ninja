package com.digimoplus.foodninja.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.network.AuthService
import javax.inject.Inject

class SignUpRepositoryImpl
@Inject constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
) : SignUpRepository {

    // register user to server
    override suspend fun registerUser(name: String, email: String, password: String): Register {
        try {
            val response = authService.signUp(name, email, password, password)
            return when (response.code()) {
                422 -> {
                    Register.WrongError
                }
                200 -> {
                    // save auth token and user id
                    saveToDataStore(
                        token = response.body()?.accessToken ?: "",
                        id = response.body()?.id ?: 0)
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

    // save auth token and user id to datastore
    private suspend fun saveToDataStore(token: String, id: Int) {

        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userId] = id
            preferences[PreferencesKeys.authenticationKey] = "Bearer $token"
        }

    }
}