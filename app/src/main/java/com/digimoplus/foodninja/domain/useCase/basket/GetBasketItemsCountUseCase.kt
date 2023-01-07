package com.digimoplus.foodninja.domain.useCase.basket

import com.digimoplus.foodninja.domain.repository.BasketRepository
import javax.inject.Inject

class GetBasketItemsCountUseCase
@Inject
constructor(
    private val basketRepository: BasketRepository,
) {

    suspend operator fun invoke() = basketRepository.getBasketItemsCount()

}