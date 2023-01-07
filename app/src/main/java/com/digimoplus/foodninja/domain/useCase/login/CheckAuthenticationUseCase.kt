package com.digimoplus.foodninja.domain.useCase.login

import com.digimoplus.foodninja.domain.repository.LoginRepository
import javax.inject.Inject

class CheckAuthenticationUseCase
@Inject
constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke() = loginRepository.checkToken()

}