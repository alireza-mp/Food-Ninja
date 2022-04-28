package com.digimoplus.foodninja.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.digimoplus.foodninja.domain.model.Register
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.domain.util.PreferencesKeys
import com.digimoplus.foodninja.network.AuthService
import kotlinx.coroutines.flow.first
import java.lang.Exception
import javax.inject.Inject

class UserInfoRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Preferences>
) : UserInfoRepository {

    override suspend fun addUserInformation(
        name: String,
        family: String,
        phone: String
    ): Register {
        try {
            val id = dataStore.data.first()[PreferencesKeys.userId] ?: 0
            val response = authService.addUserInfo(id, name, family, phone)
            return when (response.code()) {
                422 -> {
                    Register.WrongError
                }
                200 -> {
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
}