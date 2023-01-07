package com.digimoplus.foodninja.domain.useCase.register

import com.digimoplus.foodninja.domain.repository.RegisterRepository
import javax.inject.Inject

class CompleteRegisterUseCase
@Inject
constructor(
    private val registerRepository: RegisterRepository,
) {
    suspend operator fun invoke(
        name: String,
        family: String,
        phone: String,
        location: String,
    ) = registerRepository.completeRegister(name, family, phone, location)
}