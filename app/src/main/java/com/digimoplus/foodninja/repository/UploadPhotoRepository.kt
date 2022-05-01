package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Register
import java.io.InputStream

interface UploadPhotoRepository {

    suspend fun uploadProfile(inputStream: InputStream):Register

}