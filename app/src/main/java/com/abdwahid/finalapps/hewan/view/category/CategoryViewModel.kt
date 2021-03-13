package com.abdwahid.finalapps.hewan.view.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdwahid.finalapps.data.RetrofitClient
import com.abdwahid.finalapps.hewan.model.CategoryResponse
import com.abdwahid.finalapps.hewan.model.DataCategory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryViewModel : ViewModel() {
    private val data = MutableLiveData<CategoryResponse>()

    //Function Get All Data
    fun getData(): LiveData<CategoryResponse>{
        RetrofitClient.retrofitCategory().getCategoryHewan().apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if(it.isSuccess == true){
                    data.postValue(it)
                }
            }, {
                Log.d("Category View Model", it.localizedMessage)
            })
        }

        return data
    }


    //Function Insert
    fun addData(title: String): LiveData<CategoryResponse>{
        RetrofitClient.retrofitCategory().insertCategoryHewan(title).apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if (it.isSuccess == true){
                    data.postValue(it)
                }
            }, {
                Log.d("Category View Model", it.localizedMessage)
            })
        }

        return data
    }

    //Function Update
    fun updateData(id: String, title: String): LiveData<CategoryResponse>{
        RetrofitClient.retrofitCategory().updateCategoryHewan(id, title).apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if (it.isSuccess == true){
                    data.postValue(it)
                }
            }, {
                Log.d("Category View Model", it.localizedMessage)
            })
        }

        return data
    }

    //Function Delete
    fun deleteData(id: String): LiveData<CategoryResponse>{
        RetrofitClient.retrofitCategory().deleteCategoryHewan(id).apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if (it.isSuccess == true){
                    data.postValue(it)
                }
            }, {
                Log.d("Category View Model", it.localizedMessage)
            })
        }

        return data
    }

}