package com.abdwahid.finalapps.hewan.view.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdwahid.finalapps.data.RetrofitClient
import com.abdwahid.finalapps.hewan.model.DataHewan
import com.abdwahid.finalapps.hewan.model.DataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class DataViewModel : ViewModel(){
    private var composite = CompositeDisposable()
    private val list = MutableLiveData<DataResponse>()

    fun getAllData(): LiveData<DataResponse> {
        RetrofitClient.retrofitData().getAllData().apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if (it.isSuccess == true){
                    list.postValue(it)
                }
            }, {
                Log.d("Data View Model", it.localizedMessage)
            })
        }

        return list
    }

    fun insertData(category_id: RequestBody, title: RequestBody, description: RequestBody, image: MultipartBody.Part): LiveData<DataResponse> {
        composite.add(RetrofitClient.retrofitData().insertData(category_id, title, description, image)
            .doOnError {
                Log.d("Data View Model", it.localizedMessage)
            }
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccess == true){
                        list.postValue(it)
                    }
                }, {})
        )

        return list
    }

    fun updateData(id: RequestBody,category_id: RequestBody, title: RequestBody, description: RequestBody, image: MultipartBody.Part): LiveData<DataResponse> {
        composite.add(RetrofitClient.retrofitData().updateData(id, category_id, title, description, image)
                .doOnError {
                    Log.d("Data View Model", it.localizedMessage)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccess == true){
                        list.postValue(it)
                    }
                }, {})
        )

        return list
    }

    fun deleteData(id: String): LiveData<DataResponse>{
        RetrofitClient.retrofitData().deleteData(id).apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if (it.isSuccess == true){
                    list.postValue(it)
                }
            }, {
                Log.d("Data View Model", it.localizedMessage)
            })
        }

        return list
    }
}