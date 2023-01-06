package com.digimoplus.foodninja.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.repository.LoginReposiotry
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.data.api.AuthService
import javax.inject.Inject

class LoginReposiotryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
) : LoginReposiotry {

    // request to server for login user
    override suspend fun loginUser(email: String, password: String): Register {
        try {
            val results = authService.signIn(email, password)
            return when (results.code()) {
                422 -> {
                    Register.WrongError
                }
                401 -> {
                    Register.InvalidError
                }
                200 -> {
                    // save user auth token and id
                    saveToDataStore(
                        token = results.body()?.accessToken,
                        userId = results.body()?.id
                    )
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

    // save user auth token and user id
    // save user information
    private suspend fun saveToDataStore(token: String?, userId: Int?) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.authenticationKey] = "Bearer $token"
            preferences[PreferencesKeys.userInformation] = "OK"
            preferences[PreferencesKeys.userId] = userId ?: 0
        }
    }

}