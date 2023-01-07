package com.digimoplus.foodninja.domain.useCase.basket

import com.digimoplus.foodninja.domain.model.MenuDetailInfo
import com.digimoplus.foodninja.domain.repository.BasketRepository
import javax.inject.Inject

class AddNewItemToBasketUseCase
@Inject
constructor(
    private val basketRepository: BasketRepository,
) {
    suspend operator fun invoke(menuDetailInfo: MenuDetailInfo) =
        basketRepository.addNewItemToBasket(menuDetailInfo)
}