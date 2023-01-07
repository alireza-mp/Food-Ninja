package com.digimoplus.foodninja.domain.useCase.register

import com.digimoplus.foodninja.domain.repository.RegisterRepository
import java.io.InputStream
import javax.inject.Inject

class UploadProfileImageUseCase
@Inject
constructor(
    private val registerRepository: RegisterRepository,
) {

    suspend operator fun invoke(inputStream: InputStream) =
        registerRepository.uploadProfileImage(inputStream)

}