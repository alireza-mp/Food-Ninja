package com.digimoplus.foodninja.network.model

import com.digimoplus.foodninja.domain.model.Restaurant
import com.digimoplus.foodninja.domain.util.Constants
import com.digimoplus.foodninja.domain.util.DomainMapper

class RestaurantDtoMapper : DomainMapper<RestaurantDto, Restaurant> {

    override fun mapToDomainModel(model: RestaurantDto): Restaurant {
        return Restaurant(id = model.id,
            name = model.name,
            imageUrl = model.imageUrl,
            location = model.location)
    }

    override fun mapFromDomainModel(domainModel: Restaurant): RestaurantDto {
        return RestaurantDto(id = domainModel.id,
            name = domainModel.name,
            imageUrl = domainModel.imageUrl,
            location = domainModel.location)
    }

    fun mapToDomainList(list: List<RestaurantDto>): List<Restaurant> {
        return list.map { mapToDomainModel(it) }
    }

}