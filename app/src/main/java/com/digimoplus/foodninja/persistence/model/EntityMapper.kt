package com.digimoplus.foodninja.persistence.model

interface EntityMapper<Entity, DomainModel> {

    fun mapToDomainModel(model: Entity): DomainModel
    fun mapFromDomainModel(model: DomainModel): Entity

}