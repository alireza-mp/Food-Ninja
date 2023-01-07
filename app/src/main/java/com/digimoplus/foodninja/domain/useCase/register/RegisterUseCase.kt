package com.digimoplus.foodninja.domain.useCase.register

import com.digimoplus.foodninja.domain.model.RegisterState
import com.digimoplus.foodninja.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase
@Inject
constructor(
    private val registerRepository: RegisterRepository,
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        checkOneState: Boolean,
        checkTwoState: Boolean,
    ): Flow<RegisterState<Nothing?>> {
        return when {
            name.isEmpty() -> {
                flow { emit(RegisterState.InvalidNameError) }
            }
            password.length < 7 || email.length < 9 -> {
                flow { emit(RegisterState.InvalidError) }
            }
            else -> {
                registerRepository.registerUser(name, email, password)
            }
        }
    }

}