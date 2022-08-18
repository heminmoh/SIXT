/**
 * getListObservable is a function in HitViewModel that take context and searched data
 * and return  LiveData<HitModel> object
 * 2022-06-18 18:00
 */

package com.coding.sixt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coding.sixt.model.CarPreview
import com.coding.sixt.repository.CarRepositoryImp

class CarViewModel() : ViewModel() {
    private var recyclerLiveData : MutableLiveData<List<CarPreview>> = MutableLiveData()



    fun getListObservable (context: Context) : LiveData<List<CarPreview>>
    {
        recyclerLiveData = CarRepositoryImp().makeApiCall(context)
        return recyclerLiveData
    }


}