package com.digimoplus.foodninja.domain.useCase.menus

import com.digimoplus.foodninja.domain.repository.MenuRepository
import javax.inject.Inject

class GetPopularMenusListUseCase
@Inject
constructor(
    private val menuRepository: MenuRepository,
) {
    suspend operator fun invoke() = menuRepository.getPopularMenuList()
}