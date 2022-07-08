package com.digimoplus.foodninja.repository

interface MainRepository  {

    // check basket is empty or not and get count of items
    suspend fun checkBasketItemsCount():Int

}