package com.abdwahid.finalapps.data

import com.abdwahid.finalapps.wisata.model.WisataResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWisata {

    //Get Data
    @GET("getDataWisata.php")
    fun getAllWisata(): Observable<WisataResponse>

    @GET("getDataWisata.php")
    fun getDataById(@Query("category_id") category_id: Int
    ): Observable<WisataResponse>
}