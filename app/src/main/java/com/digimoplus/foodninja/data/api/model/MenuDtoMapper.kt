package com.digimoplus.foodninja.data.api.model

import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.util.DomainMapper

class MenuDtoMapper : DomainMapper<MenuDto, Menu> {
    override fun mapToDomainModel(model: MenuDto): Menu {
        return Menu(
            id = model.id,
            restaurantId = model.restaurantId,
            name = model.name,
            restaurantName = model.restaurantName,
            imageUrl = model.imageUrl,
            price = model.price
        )
    }

    override fun mapFromDomainModel(domainModel: Menu): MenuDto {
        return MenuDto(
            id = domainModel.id,
            restaurantId = domainModel.restaurantId,
            name = domainModel.name,
            restaurantName = domainModel.restaurantName,
            imageUrl = domainModel.imageUrl,
            price = domainModel.price
        )
    }

    fun mapToDomainList(list: List<MenuDto>): List<Menu> {
        return list.map { mapToDomainModel(it) }
    }
}