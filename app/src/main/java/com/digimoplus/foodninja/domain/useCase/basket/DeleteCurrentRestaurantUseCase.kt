package com.digimoplus.foodninja.domain.useCase.basket

import com.digimoplus.foodninja.domain.repository.BasketRepository
import javax.inject.Inject

class DeleteCurrentRestaurantUseCase
@Inject
constructor(
    private val basketRepository: BasketRepository,
) {
    suspend operator fun invoke(restaurantId: Int) =
        basketRepository.deleteCurrentRestaurant(restaurantId)
}