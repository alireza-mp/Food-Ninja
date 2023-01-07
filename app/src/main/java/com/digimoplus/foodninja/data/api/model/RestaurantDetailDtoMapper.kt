package com.digimoplus.foodninja.data.api.model

import com.digimoplus.foodninja.domain.model.RestaurantDetail
import com.digimoplus.foodninja.domain.model.RestaurantDetailComment
import com.digimoplus.foodninja.domain.model.RestaurantDetailInfo
import com.digimoplus.foodninja.domain.model.RestaurantDetailMenu
import com.digimoplus.foodninja.domain.util.DomainMapper

class RestaurantDetailDtoMapper : DomainMapper<RestaurantDetailDto, RestaurantDetail> {

    override fun mapToDomainModel(model: RestaurantDetailDto): RestaurantDetail {
        return RestaurantDetail(
            restaurantDetailComment = RestoDetailCommentMapper().mapToDomainList(model.restoDetailCommentDtos),
            restaurantDetailInfo = RestoDetailInfoMapper().mapToDomainModel(model.restoDetailInfoDto),
            restaurantDetailMenus = RestoDetailMenuMapper().mapToDomainList(model.restoDetailMenusDto)
        )
    }

    override fun mapFromDomainModel(domainModel: RestaurantDetail): RestaurantDetailDto {
        TODO("Not yet implemented")
    }

    inner class RestoDetailCommentMapper :
        DomainMapper<RestoDetailCommentDto, RestaurantDetailComment> {
        override fun mapToDomainModel(model: RestoDetailCommentDto): RestaurantDetailComment {
            return RestaurantDetailComment(
                date = model.date,
                description = model.description,
                id = model.id,
                imageUrl = model.imageUrl,
                name = model.name,
                rate = model.rate,
                restaurantId = model.restaurantId
            )
        }

        override fun mapFromDomainModel(domainModel: RestaurantDetailComment): RestoDetailCommentDto {
            TODO("Not yet implemented")
        }

        fun mapToDomainList(list: List<RestoDetailCommentDto>): List<RestaurantDetailComment> {
            return list.map { mapToDomainModel(it) }
        }
    }

    inner class RestoDetailMenuMapper : DomainMapper<RestoDetailMenuDto, RestaurantDetailMenu> {

        override fun mapToDomainModel(model: RestoDetailMenuDto): RestaurantDetailMenu {
            return RestaurantDetailMenu(
                id = model.id,
                imageUrl = model.imageUrl,
                name = model.name,
                price = model.price,
                restaurantId = model.restaurantId,
                restaurantName = model.restaurantName
            )
        }

        override fun mapFromDomainModel(domainModel: RestaurantDetailMenu): RestoDetailMenuDto {
            TODO("Not yet implemented")
        }

        fun mapToDomainList(list: List<RestoDetailMenuDto>): List<RestaurantDetailMenu> {
            return list.map { mapToDomainModel(it) }
        }
    }

    inner class RestoDetailInfoMapper : DomainMapper<RestoDetailInfoDto, RestaurantDetailInfo> {
        override fun mapToDomainModel(model: RestoDetailInfoDto): RestaurantDetailInfo {
            return RestaurantDetailInfo(
                desc = model.desc,
                id = model.id,
                imageUrl = model.imageUrl,
                imgDetail = model.imgDetail,
                location = model.location,
                locationKm = model.locationKm,
                name = model.name,
                rate = model.rate,
                title = model.title
            )
        }

        override fun mapFromDomainModel(domainModel: RestaurantDetailInfo): RestoDetailInfoDto {
            TODO("Not yet implemented")
        }
    }

}