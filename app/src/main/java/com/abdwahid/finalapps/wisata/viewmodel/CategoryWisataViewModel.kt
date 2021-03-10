package com.abdwahid.finalapps.wisata.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdwahid.finalapps.data.RetrofitClient
import com.abdwahid.finalapps.wisata.model.DataWisata
import com.abdwahid.finalapps.wisata.model.WisataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryWisataViewModel : ViewModel() {
    private val list = MutableLiveData<WisataResponse>()

    fun getByCategory(category_id: Int): LiveData<WisataResponse>{
        RetrofitClient.retrofitConnect().getDataById(category_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.postValue(it)
//                Log.d("Category ViewModel", it.data.toString())
            }, {
                Log.d("Category ViewModel", it.localizedMessage)
            })

        return list
    }
}