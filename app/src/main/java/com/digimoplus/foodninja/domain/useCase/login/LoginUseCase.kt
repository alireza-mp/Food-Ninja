package com.digimoplus.foodninja.domain.useCase.login

import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase
@Inject
constructor(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Flow<RegisterState<Nothing?>> {
        return when {
            email.length < 9 || password.length < 7 -> {
                flow { emit(RegisterState.InvalidError) }
            }
            else -> {
                loginRepository.login(email, password)
            }
        }
    }
}