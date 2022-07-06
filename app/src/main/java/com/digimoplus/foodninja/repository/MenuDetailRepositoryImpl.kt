package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.DataState
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.network.AuthService
import com.digimoplus.foodninja.network.model.MenuDetailDtoMapper
import javax.inject.Inject


class MenuDetailRepositoryImpl
@Inject
constructor(
    private val authService: AuthService,
    private val mapper :MenuDetailDtoMapper
) : MenuDetailRepository {

    override suspend fun getMenuDetails(token: String, menuId: Int): DataState<MenuDetail> {
        return try {
            val result = authService.menuDetails(token = token, id = menuId)
            when (result.code()) {
                404 -> {
                    DataState.SomeError()
                }
                200 -> {
                    val data = result.body()
                    if (data != null) {
                        DataState.Success(
                            mapper.mapToDomainModel(data)
                        )
                    } else {
                        DataState.SomeError()
                    }
                }
                else -> {
                    DataState.SomeError()
                }
            }
        } catch (e: Exception) {
            DataState.NetworkError()
        }
    }

}