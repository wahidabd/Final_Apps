package com.abdwahid.finalapps.hewan.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdwahid.finalapps.data.RetrofitClient
import com.abdwahid.finalapps.hewan.model.DataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val list = MutableLiveData<DataResponse>()

    fun getAllData(): LiveData<DataResponse>{
        RetrofitClient.retrofitData().getAllData().apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe({
                if (it.isSuccess == true){
                    list.postValue(it)
                }
            }, {
                Log.d("Home View Model", it.localizedMessage)
            })
        }

        return list
    }
}