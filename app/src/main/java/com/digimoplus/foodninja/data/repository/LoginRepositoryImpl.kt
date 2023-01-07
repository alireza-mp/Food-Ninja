package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.repository.LoginRepository
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source.LoginRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl
@Inject
constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val dataStore: DataStoreLocalDataSource,
) : LoginRepository {

    override suspend fun login(email: String, password: String): Flow<RegisterState<Nothing?>> =
        flow {
            emit(RegisterState.Loading)
            val result = loginRemoteDataSource.login(email, password)
            result ?: emit(RegisterState.NetworkError)
            result?.let { resultData ->
                when (resultData.code()) {
                    422 -> {
                        emit(RegisterState.WrongError)
                    }
                    401 -> {
                        emit(RegisterState.InvalidError)
                    }
                    200 -> {
                        resultData.body() ?: emit(RegisterState.SomeError)
                        resultData.body()?.let { body ->
                            dataStore.saveUserId(body.id)
                            dataStore.saveToken(body.accessToken)
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

    override suspend fun checkToken(): String {
        return dataStore.getToken()
    }

}