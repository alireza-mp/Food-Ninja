package com.digimoplus.foodninja.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterInfo(
    val name: String? = null,
    val family: String? = null,
    val phone: String? = null,
): Parcelable