package com.digimoplus.foodninja.repository

import com.digimoplus.foodninja.domain.model.Register
import java.io.InputStream

interface UploadPhotoRepository {

    // upload profile image to server and get profile image url
    suspend fun uploadProfile(inputStream: InputStream): Register

}