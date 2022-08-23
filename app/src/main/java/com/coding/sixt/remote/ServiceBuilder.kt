/**
 * This class build a retrofit Api interface
 * okHttp  has the cache ability equals to cacheObject 'cacheSize' variable
 *  an HTTP annotation that provides the request method and relative URL
 *  2022-08-18  19:30
 */

package com.coding.sixt.remote

import android.content.Context
import com.coding.sixt.utilitiy.CheckInternetConnection
import com.coding.sixt.utilitiy.HelperSIXT
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
 class ServiceBuilder (context: Context) {

    private val cacheObject= Cache(context.cacheDir, HelperSIXT.CACHE_SIZE)
    private val checkInternetConnection= CheckInternetConnection()

    private val okHttp = OkHttpClient.Builder().cache(cacheObject).addInterceptor{
        chain ->  var request = chain.request()
                 request = if (checkInternetConnection.checkForInternet(context))
            request.newBuilder().header("Cache-Control", "public, max-age=" + 4).build()
        else
            request.newBuilder().header(
                "Cache-Control",
                "public, only-if-cached, max-stale=" + HelperSIXT.WEEK_TIME
            ).build()
        chain.proceed(request)
    }

    private val builder =Retrofit.Builder().baseUrl(HelperSIXT.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

     val retrofit: Retrofit = builder.build()

    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }


}