package com.abdwahid.finalapps.hewan.model

data class DataResponse(
        val data: ArrayList<DataHewan>? = null,
        val isSuccess: Boolean? = null,
        val message: String? = null
)