package com.digimoplus.foodninja.data.repository

import com.digimoplus.foodninja.domain.repository.ProfileRepository
import com.digimoplus.foodninja.domain.repository.data_source.DataStoreLocalDataSource
import javax.inject.Inject

class ProfileRepositoryImpl
@Inject
constructor(
    private val dataStoreLocalDataSource: DataStoreLocalDataSource,
) : ProfileRepository {

    override suspend fun getProfileImageUrl(): String = dataStoreLocalDataSource.getUserProfileUrl()

    override suspend fun saveProfileImageUrl(url: String) {
        dataStoreLocalDataSource.saveUserProfileUrl(url)
    }

}