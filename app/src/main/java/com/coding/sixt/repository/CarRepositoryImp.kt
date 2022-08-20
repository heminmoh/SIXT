/**
 * HitRepositoryImp is an class call Api by  Coroutine method
 * take an id which is the search key and return a MutableLiveData<HitModel>
 *  2022-06-18  15:37
 */
package com.coding.sixt.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.coding.sixt.model.CarPreview
import com.coding.sixt.remote.APIInterface
import com.coding.sixt.remote.ServiceBuilder
import com.coding.sixt.repositorymodel.ICarRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class CarRepositoryImp :  ICarRepository {
    override var recyclerLiveData : MutableLiveData<List<CarPreview>> = MutableLiveData()
    @OptIn(DelicateCoroutinesApi::class)
    override fun makeApiCall(context: Context ) : MutableLiveData<List<CarPreview>>
    {
        GlobalScope.launch (Dispatchers.IO) {
            val serviceBuilder = ServiceBuilder(context)
            val destinationService  = serviceBuilder.buildService(APIInterface::class.java)
            val requestCall =destinationService.getCars()
            requestCall.enqueue(object : Callback<List<CarPreview>> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: retrofit2.Call<List<CarPreview>>, response: Response<List<CarPreview>>) {
                    if (response.isSuccessful){
                        recyclerLiveData.value = response.body()
                    }
                }
                override fun onFailure(call: retrofit2.Call<List<CarPreview>>, t: Throwable) {
                }
            })
        }
       return recyclerLiveData
    }

}
