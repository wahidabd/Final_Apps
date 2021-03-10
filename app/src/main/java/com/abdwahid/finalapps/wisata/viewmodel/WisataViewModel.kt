package com.abdwahid.finalapps.wisata.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdwahid.finalapps.data.RetrofitClient
import com.abdwahid.finalapps.wisata.model.WisataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class WisataViewModel : ViewModel(){
    private val list = MutableLiveData<WisataResponse>()

    fun getAllData(): LiveData<WisataResponse>{
        RetrofitClient.retrofitConnect().getAllWisata().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.postValue(it)
//                Log.d("Wisata ViewModel", it.data.toString())
            }, {
                Log.d("Wisata ViewModel", it.localizedMessage)
            })

        return list
    }
}