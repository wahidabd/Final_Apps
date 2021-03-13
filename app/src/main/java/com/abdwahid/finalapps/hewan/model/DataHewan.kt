package com.abdwahid.finalapps.hewan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataHewan(
        val id: String? = null,
        val image: String? = null,
        val category_id: String? = null,
        val title: String? = null,
        val description: String? = null
): Parcelable