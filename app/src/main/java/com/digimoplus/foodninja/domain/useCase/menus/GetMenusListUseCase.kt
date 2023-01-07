package com.digimoplus.foodninja.domain.useCase.menus

import com.digimoplus.foodninja.domain.repository.MenuRepository
import javax.inject.Inject

class GetMenusListUseCase
@Inject
constructor(
    private val menuRepository: MenuRepository,
) {

    suspend operator fun invoke(page: Int) = menuRepository.getMenusList(page)

}