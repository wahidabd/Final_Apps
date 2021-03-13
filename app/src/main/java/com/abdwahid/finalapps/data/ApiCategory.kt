package com.abdwahid.finalapps.data

import com.abdwahid.finalapps.hewan.model.CategoryResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCategory {

    //Get Data Category Hewan
    @GET("hewanCategory.php")
    fun getCategoryHewan(): Observable<CategoryResponse>

    //Insert
    @FormUrlEncoded
    @POST("hewanCategoryInsert.php")
    fun insertCategoryHewan(
            @Field("category_name") category_name: String
    ): Observable<CategoryResponse>

    //Update
    @FormUrlEncoded
    @POST("hewanCategoryUpdate.php")
    fun updateCategoryHewan(
            @Field("id") id: String,
            @Field("category_name") category_name: String
    ): Observable<CategoryResponse>

    //Delete
    @FormUrlEncoded
    @POST("hewanCategoryDelete.php")
    fun deleteCategoryHewan(
            @Field("id") id: String
    ): Observable<CategoryResponse>
}