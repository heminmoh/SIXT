/**
 * getListObservable is a function in HitViewModel that take context and searched data
 * and return  LiveData<HitModel> object
 * 2022-06-18 18:00
 */

package com.coding.sixt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coding.sixt.model.CarPreview

class MapViewModel : ViewModel() {
    private var recyclerLiveData : MutableLiveData<CarPreview> = MutableLiveData()
    val details: LiveData<CarPreview> = recyclerLiveData
     fun fetchCarDetails(carPreview: CarPreview) {
         recyclerLiveData.value = carPreview
    }


}