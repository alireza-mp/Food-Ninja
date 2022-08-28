package com.digimoplus.foodninja.data.db.model

interface EntityMapper<Entity, DomainModel> {

    fun mapToDomainModel(model: Entity): DomainModel
    fun mapFromDomainModel(model: DomainModel): Entity

}