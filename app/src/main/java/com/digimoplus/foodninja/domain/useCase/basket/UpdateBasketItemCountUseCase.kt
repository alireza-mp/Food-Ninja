package com.digimoplus.foodninja.domain.useCase.basket

import com.digimoplus.foodninja.domain.repository.BasketRepository
import javax.inject.Inject

class UpdateBasketItemCountUseCase
@Inject
constructor(
    private val basketRepository: BasketRepository,
) {

    suspend operator fun invoke(id: Int, count: Int) {
        basketRepository.updateCount(id, count)
    }

}