package com.digimoplus.foodninja.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.network.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>,
) : SignInRepository {

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
                    saveUser(
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

    // save user authentication key
    // save user information key
    private suspend fun saveUser(token: String?, userId: Int?) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.authenticationKey] = "Bearer $token"
            preferences[PreferencesKeys.userInformation] = "OK"
            preferences[PreferencesKeys.userId] = userId ?: 0
        }
    }

}