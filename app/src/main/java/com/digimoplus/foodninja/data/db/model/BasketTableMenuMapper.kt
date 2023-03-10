package com.digimoplus.foodninja.data.db.model

import com.digimoplus.foodninja.domain.model.MenuDetailInfo

class BasketTableMenuMapper : EntityMapper<BasketTable, MenuDetailInfo> {
    override fun mapToDomainModel(model: BasketTable): MenuDetailInfo {
        return MenuDetailInfo(
            descriptionTop = "",
            descriptionBottom = "",
            id = model.id,
            imageUrl = model.imageUrl,
            imageDetail = "",
            locationKm = "",
            name = model.name,
            price = model.price,
            rate = "",
            restaurantName = model.restoName,
            restaurantId = -1,
            title = ""
        )
    }

    override fun mapFromDomainModel(model: MenuDetailInfo): BasketTable {
        return BasketTable(
            id = 0,
            userId = 0,
            menuId = model.id,
            count = 0,
            name = model.name,
            restoName = model.restaurantName,
            price = model.price,
            imageUrl = model.imageUrl,
            restoId = model.restaurantId
        )
    }

    fun mapFromDomainModel(
        model: MenuDetailInfo,
        userId: Int,
        count: Int,
        id: Int,
    ): BasketTable {
        val entityModel = mapFromDomainModel(model)
        entityModel.id = id
        entityModel.userId = userId
        entityModel.count = count
        return entityModel
    }

}