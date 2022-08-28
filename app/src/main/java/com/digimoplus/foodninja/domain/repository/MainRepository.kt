package com.digimoplus.foodninja.domain.repository

interface MainRepository  {

    // check basket is empty or not and get count of items
    suspend fun checkBasketItemsCount():Int

}