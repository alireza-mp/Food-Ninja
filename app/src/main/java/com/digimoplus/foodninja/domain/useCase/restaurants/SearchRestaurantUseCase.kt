package com.digimoplus.foodninja.domain.useCase.restaurants

import com.digimoplus.foodninja.domain.repository.RestaurantRepository
import javax.inject.Inject

class SearchRestaurantUseCase
@Inject
constructor(
    private val restaurantRepository: RestaurantRepository,
) {

    suspend operator fun invoke(search: String, page: Int) =
        restaurantRepository.restaurantSearch(search, page)


}