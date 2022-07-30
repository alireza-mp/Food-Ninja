package com.digimoplus.foodninja.network.model

import com.digimoplus.foodninja.domain.model.Restaurant
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

/*

fun mapToDomain2DList(list: List<RestaurantDto>): List<List<Restaurant>> {

    // if list.size is odd remove one index
    val length = if (list.size / 2 == 0) list.size - 1 else list.size

    val restaurants2D = mutableListOf<List<Restaurant>>()

    // one row = two item in a list
    for (index in 0..length step 2) {
        val restaurantRow = mutableListOf<Restaurant>()
        restaurantRow.add(mapToDomainModel(list[index]))
        restaurantRow.add(mapToDomainModel(list[index + 1]))
        restaurants2D.add(restaurantRow)
    }

    //if list.size odd add one last item
    if (list.size / 2 == 0) {
        val restaurantRow = mutableListOf<Restaurant>()
        restaurantRow.add(mapToDomainModel(list[index]))
        restaurantRow.add(mapToDomainModel(list[index + 1]))
        restaurants2D.add(restaurantRow)
    }

    return restaurants2D
}*/
