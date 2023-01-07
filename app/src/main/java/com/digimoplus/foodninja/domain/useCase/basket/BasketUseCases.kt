package com.digimoplus.foodninja.domain.useCase.basket

data class BasketUseCases(
    val getBasketListUseCase: GetBasketListUseCase,
    val updateBasketItemCountUseCase: UpdateBasketItemCountUseCase,
    val getBasketItemCountUseCase: GetBasketItemCountUseCase,
    val deleteBasketItemUseCase: DeleteBasketItemUseCase,
    val deleteCurrentRestaurantUseCase: DeleteCurrentRestaurantUseCase,
    val getCurrentRestaurantUseCase: GetCurrentRestaurantUseCase,
    val addNewItemToBasketUseCase: AddNewItemToBasketUseCase,
)