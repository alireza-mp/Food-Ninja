package com.digimoplus.foodninja.domain.useCase.register

import com.digimoplus.foodninja.domain.repository.RegisterRepository
import javax.inject.Inject

class SaveIntroductionUseCase
@Inject
constructor(
    private val registerRepository: RegisterRepository,
) {
    suspend operator fun invoke(state: Boolean) = registerRepository.saveIntroduction(state)
}