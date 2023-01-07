package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.repository.RegisterRepository
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source.RegisterRemoteDataSource
import com.digimoplus.foodninja.domain.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStream
import javax.inject.Inject

class RegisterRepositoryImpl
@Inject constructor(
    private val registerRemoteDataSource: RegisterRemoteDataSource,
    private val dataStore: DataStoreLocalDataSource,
) : RegisterRepository {

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Flow<RegisterState<Nothing?>> = flow {

        emit(RegisterState.Loading)
        val result = registerRemoteDataSource.registerUser(name, email, password)
        result ?: emit(RegisterState.NetworkError)
        result?.let { resultData ->
            when (resultData.code()) {
                422 -> {
                    emit(RegisterState.WrongError)
                }
                200 -> {
                    // save auth token and user id
                    resultData.body() ?: emit(RegisterState.SomeError)
                    resultData.body()?.let { body ->
                        dataStore.saveToken(body.accessToken)
                        dataStore.saveUserId(body.id)
                        emit(RegisterState.Successful(null))
                    }
                }
                else -> {
                    emit(RegisterState.SomeError)
                }
            }
        }
    }

    override suspend fun uploadProfileImage(inputStream: InputStream): Flow<RegisterState<String>> =
        flow {
            emit(RegisterState.Loading)
            val result = registerRemoteDataSource.uploadProfileImage(
                id = dataStore.getUserId().toString(),
                inputStream = inputStream,
            )
            result ?: emit(RegisterState.NetworkError)
            result?.let { resultData ->
                when (resultData.code()) {
                    422 -> {
                        emit(RegisterState.InvalidError)
                    }
                    200 -> {
                        resultData.body() ?: emit(RegisterState.SomeError)
                        resultData.body()?.let { body ->
                            val profileUrl = Constants.BASE_URL + body.message
                            dataStore.saveUserProfileUrl(profileUrl)
                            emit(RegisterState.Successful(profileUrl)) // message is profile url
                        }
                    }
                    else -> {
                        emit(RegisterState.SomeError)
                    }
                }
            }
        }

    override suspend fun completeRegister(
        name: String,
        family: String,
        phone: String,
        location: String,
    ): Flow<RegisterState<Nothing?>> = flow {
        emit(RegisterState.Loading)
        val result = registerRemoteDataSource.completeUserRegister(
            id = dataStore.getUserId(),
            name = name,
            family = family,
            phone = phone,
            location = location
        )
        result ?: emit(RegisterState.NetworkError)
        result?.let { resultData ->
            when (resultData.code()) {
                422 -> {
                    emit(RegisterState.WrongError)
                }
                200 -> {
                    resultData.body() ?: emit(RegisterState.SomeError)
                    resultData.body()?.let {
                        dataStore.saveCompleteRegister(true)
                        emit(RegisterState.Successful(null))
                    }
                }
                else -> {
                    emit(RegisterState.SomeError)
                }
            }
        }

    }

    override suspend fun getCompleteUserRegister(): String =
        dataStore.getCompleteRegister()

    override suspend fun savePaymentMethod(payment: String) {
        dataStore.saveUserPaymentMethod(payment)
    }

    override suspend fun saveIntroduction(state: Boolean) {
        dataStore.saveIntroduction(state)
    }

    override suspend fun checkIntroduction(): String = dataStore.getUserIntroduction()


}