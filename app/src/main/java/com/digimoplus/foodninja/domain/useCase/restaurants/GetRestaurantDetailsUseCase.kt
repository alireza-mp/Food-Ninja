package com.digimoplus.foodninja.domain.useCase.restaurants

import com.digimoplus.foodninja.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantDetailsUseCase
@Inject
constructor(
    private val restaurantRepository: RestaurantRepository,
) {

    suspend operator fun invoke(restaurantId: Int) =
        restaurantRepository.getRestaurantDetails(restaurantId)

}