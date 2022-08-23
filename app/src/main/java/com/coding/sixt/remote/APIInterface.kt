/**
 * an interface to provide HTTP request
 *  HTTP annotation  provides the request method and relative URL
 *  It returns a list od objects (CarPreView)
 * 2022-08-18 2030
 */

package com.coding.sixt.remote


import com.coding.sixt.model.CarPreview
import retrofit2.Call
import retrofit2.http.GET


  interface APIInterface {
    @GET("cars")
    fun getCars() : Call<List<CarPreview>>
}