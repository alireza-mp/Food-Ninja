package com.digimoplus.foodninja.domain.useCase.profile

import com.digimoplus.foodninja.domain.repository.ProfileRepository
import javax.inject.Inject

class CheckProfileImageUrlUseCase
@Inject
constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke() = profileRepository.getProfileImageUrl()
}