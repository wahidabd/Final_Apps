package com.abdwahid.finalapps.data

import com.abdwahid.finalapps.hewan.model.DataResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiHewan {

    @GET("hewanData.php")
    fun getAllData(): Observable<DataResponse>

    @Multipart
    @POST("hewanInsert.php")
    fun insertData(
            @Part("category_id") category_id: RequestBody,
            @Part("title") title: RequestBody,
            @Part("description") description: RequestBody,
            @Part file: MultipartBody.Part
    ): Single<DataResponse>

    @Multipart
    @POST("hewanUpdate.php")
    fun updateData(
            @Part("id") id: RequestBody,
            @Part("category_id") category_id: RequestBody,
            @Part("title") title: RequestBody,
            @Part("description") description: RequestBody,
            @Part file: MultipartBody.Part
    ): Single<DataResponse>

    @FormUrlEncoded
    @POST("hewanDelete.php")
    fun deleteData(
            @Field("id") id: String? = null
    ): Observable<DataResponse>
}