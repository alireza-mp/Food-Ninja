package com.digimoplus.foodninja.persistence.model

import com.digimoplus.foodninja.domain.model.Basket

class BasketTableMapper : EntityMapper<BasketTable, Basket> {

    override fun mapToDomainModel(model: BasketTable): Basket {
        return Basket(
            id = model.id,
            userId = model.userId,
            restoId = model.restoId,
            menuId = model.menuId,
            count = model.count,
            name = model.name,
            restoName = model.restoName,
            price = model.price,
            imageUrl = model.imageUrl
        )
    }

    override fun mapFromDomainModel(model: Basket): BasketTable {
        return BasketTable(
            id = model.id,
            userId = model.userId,
            restoId = model.restoId,
            menuId = model.menuId,
            count = model.count,
            name = model.name,
            restoName = model.restoName,
            price = model.price,
            imageUrl = model.imageUrl
        )
    }

    fun mapToDomainList(list: List<BasketTable>): List<Basket> {
        return list.map { mapToDomainModel(it) }
    }

}