package com.digimoplus.foodninja.data.api.model

import com.digimoplus.foodninja.domain.model.*
import com.digimoplus.foodninja.domain.util.DomainMapper

class RestoDetailDtoMapper : DomainMapper<RestoDetailDto, RestoDetail> {

    override fun mapToDomainModel(model: RestoDetailDto): RestoDetail {
        return RestoDetail(
            restoDetailComment = RestoDetailCommentMapper().mapToDomainList(model.restoDetailCommentDtos),
            restoDetailInfo = RestoDetailInfoMapper().mapToDomainModel(model.restoDetailInfoDto),
            restoDetailMenus = RestoDetailMenuMapper().mapToDomainList(model.restoDetailMenusDto)
        )
    }

    override fun mapFromDomainModel(domainModel: RestoDetail): RestoDetailDto {
        TODO("Not yet implemented")
    }

    inner class RestoDetailCommentMapper() : DomainMapper<RestoDetailCommentDto, RestoDetailComment> {
        override fun mapToDomainModel(model: RestoDetailCommentDto): RestoDetailComment {
            return RestoDetailComment(
                date = model.date,
                description = model.description,
                id = model.id,
                imageUrl = model.imageUrl,
                name = model.name,
                rate = model.rate,
                restaurantId = model.restaurantId
            )
        }

        override fun mapFromDomainModel(domainModel: RestoDetailComment): RestoDetailCommentDto {
            TODO("Not yet implemented")
        }

        fun mapToDomainList(list: List<RestoDetailCommentDto>): List<RestoDetailComment> {
            return list.map { mapToDomainModel(it) }
        }
    }

    inner class RestoDetailMenuMapper() : DomainMapper<RestoDetailMenuDto, RestoDetailMenu> {

        override fun mapToDomainModel(model: RestoDetailMenuDto): RestoDetailMenu {
            return RestoDetailMenu(
                id = model.id,
                imageUrl = model.imageUrl,
                name = model.name,
                price = model.price,
                restaurantId = model.restaurantId,
                restaurantName = model.restaurantName
            )
        }

        override fun mapFromDomainModel(domainModel: RestoDetailMenu): RestoDetailMenuDto {
            TODO("Not yet implemented")
        }

        fun mapToDomainList(list: List<RestoDetailMenuDto>): List<RestoDetailMenu> {
            return list.map { mapToDomainModel(it) }
        }
    }

    inner class RestoDetailInfoMapper() : DomainMapper<RestoDetailInfoDto, RestoDetailInfo> {
        override fun mapToDomainModel(model: RestoDetailInfoDto): RestoDetailInfo {
            return RestoDetailInfo(
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

        override fun mapFromDomainModel(domainModel: RestoDetailInfo): RestoDetailInfoDto {
            TODO("Not yet implemented")
        }
    }

}