package com.abdwahid.finalapps.wisata.model


data class WisataResponse (
    val data: ArrayList<DataWisata>? = null,
    val isSuccess: Boolean? = null,
    val message: String? = null
)

