package com.abdwahid.finalapps.wisata.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataWisata(
    val id: Int? = null,
    val category_id: Int? = null,
    val image: String? = null,
    val title: String? = null,
    val description: String
): Parcelable