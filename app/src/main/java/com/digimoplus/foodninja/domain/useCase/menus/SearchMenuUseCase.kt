package com.digimoplus.foodninja.domain.useCase.menus

import com.digimoplus.foodninja.domain.repository.MenuRepository
import javax.inject.Inject

class SearchMenuUseCase
@Inject
constructor(
    private val menuRepository: MenuRepository,
) {

    suspend operator fun invoke(search: String, page: Int) = menuRepository.menuSearch(search, page)

}