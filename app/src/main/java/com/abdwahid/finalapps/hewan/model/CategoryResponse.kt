package com.abdwahid.finalapps.hewan.model

data class CategoryResponse(
        val data: ArrayList<DataCategory>? = null,
        val isSuccess: Boolean? = null,
        val message: String? = null
)