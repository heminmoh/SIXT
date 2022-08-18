/**
 * an interface to provide HTTP request
 *  HTTP annotation  provides the request method and relative URL
 * 2022-06-17 12:00
 */

package com.coding.sixt.remote


import com.coding.sixt.model.CarPreview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


  interface APIInterface {

    @GET("cars")
    fun getCars() : Call<List<CarPreview>>
}