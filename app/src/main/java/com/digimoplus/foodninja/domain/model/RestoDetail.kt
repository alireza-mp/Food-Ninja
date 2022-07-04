package com.digimoplus.foodninja.domain.model



data class RestoDetail(
    val restoDetailComment: List<RestoDetailComment>,
    val restoDetailInfo: RestoDetailInfo,
    val restoDetailMenus: List<RestoDetailMenu>
)