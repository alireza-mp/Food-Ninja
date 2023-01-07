package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.data.api.model.MenuDetailDtoMapper
import com.digimoplus.foodninja.data.api.model.MenuDtoMapper
import com.digimoplus.foodninja.domain.model.Menu
import com.digimoplus.foodninja.domain.model.MenuDetail
import com.digimoplus.foodninja.domain.model.MenuList
import com.digimoplus.foodninja.domain.repository.MenuRepository
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import com.digimoplus.foodninja.domain.repository.data_source.MenuRemoteDataSource
import com.digimoplus.foodninja.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRepositoryImpl
@Inject
constructor(
    private val menuRemoteDataSource: MenuRemoteDataSource,
    private val dataStore: DataStoreLocalDataSource,
    private val menuDetailDtoMapper: MenuDetailDtoMapper,
    private val menuMapper: MenuDtoMapper,
) : MenuRepository {


    //get menu list -> main content
    override suspend fun getPopularMenuList(): Flow<DataState<List<Menu>>> = flow {
        emit(DataState.Loading)
        val results = menuRemoteDataSource.getPopularMenus(dataStore.getToken())
        results ?: emit(DataState.NetworkError())
        results?.let {
            when (results.code()) {
                200 -> {
                    results.body() ?: emit(DataState.SomeError())
                    results.body()?.let {
                        emit(DataState.Success(
                            menuMapper.mapToDomainList(it)
                        ))
                    }
                }
                else -> {
                    emit(DataState.SomeError())
                }
            }
        }
    }

    //get all menu list with page : menu content
    override suspend fun getMenusList(page: Int): Flow<DataState<MenuList>> = flow {
        emit(DataState.Loading)
        val results = menuRemoteDataSource.getMenusList(dataStore.getToken(), page)
        results ?: emit(DataState.NetworkError())
        results?.let {
            when (results.code()) {
                200 -> {
                    results.body() ?: emit(DataState.SomeError())
                    results.body()?.let {
                        emit(DataState.Success(
                            MenuList(
                                currentPage = it.currentPage,
                                lastPage = it.lastPage,
                                data = menuMapper.mapToDomainList(it.data)
                            )
                        ))
                    }
                }
                else -> {
                    emit(DataState.SomeError())
                }
            }
        }
    }

    // search in menus  : menu content
    override suspend fun menuSearch(
        search: String,
        page: Int,
    ): Flow<DataState<MenuList>> = flow {
        emit(DataState.Loading)
        val results = menuRemoteDataSource.menuSearch(dataStore.getToken(), search, page)
        results ?: emit(DataState.NetworkError())
        results?.let {
            when (results.code()) {
                200 -> {
                    results.body() ?: emit(DataState.SomeError())
                    results.body()?.let {
                        emit(DataState.Success(MenuList(
                            currentPage = it.currentPage,
                            lastPage = it.lastPage,
                            data = menuMapper.mapToDomainList(it.data)
                        )))
                    }
                }
                else -> {
                    emit(DataState.SomeError())
                }
            }
        }
    }

    // get menu details by menu id : menu detail page
    override suspend fun getMenuDetails(menuId: Int): Flow<DataState<MenuDetail>> =
        flow {
            emit(DataState.Loading)
            val result = menuRemoteDataSource.getMenuDetails(dataStore.getToken(), menuId)
            result ?: emit(DataState.NetworkError())
            result?.let {
                when (result.code()) {
                    200 -> {
                        result.body() ?: emit(DataState.SomeError())
                        result.body()?.let {
                            emit(DataState.Success(menuDetailDtoMapper.mapToDomainModel(model = it)))
                        }
                    }
                    else -> {
                        emit(DataState.SomeError())
                    }
                }
            }
        }
}