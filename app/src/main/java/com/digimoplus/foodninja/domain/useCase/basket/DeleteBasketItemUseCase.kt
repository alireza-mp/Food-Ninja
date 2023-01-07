package com.digimoplus.foodninja.domain.useCase.basket

import com.digimoplus.foodninja.domain.repository.BasketRepository
import javax.inject.Inject

class DeleteBasketItemUseCase
@Inject
constructor(
    private val basketRepository: BasketRepository,
) {
    suspend operator fun invoke(id: Int) {
        basketRepository.deleteBasketItem(id = id)
    }

}