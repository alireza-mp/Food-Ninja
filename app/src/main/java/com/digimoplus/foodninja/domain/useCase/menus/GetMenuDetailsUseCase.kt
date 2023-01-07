package com.digimoplus.foodninja.domain.useCase.menus

import com.digimoplus.foodninja.domain.repository.MenuRepository
import javax.inject.Inject

class GetMenuDetailsUseCase
@Inject
constructor(
    private val menuRepository: MenuRepository,
) {
    suspend operator fun invoke(menuId: Int) = menuRepository.getMenuDetails(menuId)
}