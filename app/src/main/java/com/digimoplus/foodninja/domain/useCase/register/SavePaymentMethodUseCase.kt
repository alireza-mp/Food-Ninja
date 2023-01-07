package com.digimoplus.foodninja.domain.useCase.register

import com.digimoplus.foodninja.domain.repository.RegisterRepository
import javax.inject.Inject

class SavePaymentMethodUseCase
@Inject
constructor(
    private val registerRepository: RegisterRepository,
) {
    suspend operator fun invoke(payment: String) = registerRepository.savePaymentMethod(payment)
}