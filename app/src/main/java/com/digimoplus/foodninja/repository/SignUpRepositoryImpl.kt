package com.digimoplus.foodninja.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.network.AuthService
import javax.inject.Inject

class SignUpRepositoryImpl
@Inject constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>
) : SignUpRepository {

    override suspend fun registerUser(name: String, email: String, password: String): Register {
        try {
            val response = authService.SignUp(name, email, password, password)
            return when(response.code()){
                422->{
                    Register.InvalidError
                }
                200->{
                    saveAuthenticationToken(response.body()?.accessToken)
                    Register.Successful
                }
                else->{
                    Register.SomeError
                }
            }
        } catch (e: Exception) {
          return  Register.NetworkError
        }
    }

    private suspend fun saveAuthenticationToken(token: String?) {
        token?.let {
            Log.d(TAG, "saveAuthenticationToken: $token")
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.authenticationKey] = it
            }
        }
    }
}