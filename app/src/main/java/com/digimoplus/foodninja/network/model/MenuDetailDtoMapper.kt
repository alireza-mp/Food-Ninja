package com.digimoplus.foodninja.network.model

import com.digimoplus.foodninja.domain.model.*
import com.digimoplus.foodninja.domain.util.DomainMapper

class MenuDetailDtoMapper : DomainMapper<MenuDetailDto, MenuDetail> {

    override fun mapToDomainModel(model: MenuDetailDto): MenuDetail {
        return MenuDetail(
            menuDetailInfo = MenuDetailInfoDtoMapper().mapToDomainModel(model.menuDetailInfoDto),
            menuDetailComments = MenuDetailCommentsDtoMapper().mapToDomainList(model.menuDetailCommentsDto),
            menuDetailMaterials = MenuDetailMaterialsDtoMapper().mapToDomainList(model.menuDetailMaterialsDto)
        )
    }

    override fun mapFromDomainModel(domainModel: MenuDetail): MenuDetailDto {
        TODO("Not yet implemented")
    }

    inner class MenuDetailInfoDtoMapper : DomainMapper<MenuDetailInfoDto, MenuDetailInfo> {
        override fun mapToDomainModel(model: MenuDetailInfoDto): MenuDetailInfo {
            val parts = model.desc.split("/")
            return MenuDetailInfo(
                descriptionTop = parts[0],
                descriptionBottom = parts[1],
                id = model.id,
                imageUrl = model.imageUrl,
                imageDetail = model.imageDetail,
                locationKm = model.locationKm,
                name = model.name,
                price = model.price,
                rate = model.rate,
                restaurantId = model.restaurantId,
                restaurantName = model.restaurantName,
                title = model.title
            )
        }

        override fun mapFromDomainModel(domainModel: MenuDetailInfo): MenuDetailInfoDto {
            TODO("Not yet implemented")
        }
    }

    inner class MenuDetailCommentsDtoMapper :
        DomainMapper<MenuDetailCommentsDto, MenuDetailComments> {
        override fun mapToDomainModel(model: MenuDetailCommentsDto): MenuDetailComments {
            return MenuDetailComments(
                date = model.date,
                description = model.description,
                id = model.id,
                imageUrl = model.imageUrl,
                menuId = model.menuId,
                name = model.name,
                rate = model.rate
            )
        }

        override fun mapFromDomainModel(domainModel: MenuDetailComments): MenuDetailCommentsDto {
            TODO("Not yet implemented")
        }

        fun mapToDomainList(list: List<MenuDetailCommentsDto>): List<MenuDetailComments> {
            return list.map { mapToDomainModel(it) }
        }
    }

    inner class MenuDetailMaterialsDtoMapper :
        DomainMapper<MenuDetailMaterialsDto, String> {
        override fun mapToDomainModel(model: MenuDetailMaterialsDto): String {
            return model.name
        }

        override fun mapFromDomainModel(domainModel: String): MenuDetailMaterialsDto {
            TODO("Not yet implemented")
        }

        fun mapToDomainList(list: List<MenuDetailMaterialsDto>): List<String> {
            return list.map { mapToDomainModel(it) }
        }
    }


}