/**
 * IHitRepository is an interface for HitRepositoryImp class
 * class call Api by coroutine method
 *  2022-06-18  12:37
 */

package com.coding.sixt.repositorymodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.coding.sixt.model.CarPreview

interface ICarRepository {
    var recyclerLiveData: MutableLiveData<List<CarPreview>>
    fun makeApiCall(context: Context ) : MutableLiveData<List<CarPreview>>
}