
/**
 * This class build a retrofit Api interface
 * okHttp  has the cache ability equals to 'cacheSize' variable
 *  an HTTP annotation that provides the request method and relative URL
 *  2022-06-17  16:37
 */



package com.coding.sixt.remote



import android.content.Context
import com.coding.sixt.utilitiy.CheckInternetConnection
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
 class ServiceBuilder (context: Context) {



    private val cacheSize = (10 * 1024 * 1024).toLong()
    private val myCache = Cache(context.cacheDir, cacheSize)
    private val checkInternetConnection= CheckInternetConnection()
    val url ="https://cdn.sixt.io/codingtask/"
    private val okHttp =OkHttpClient.Builder().cache(myCache).addInterceptor{
        chain ->  var request = chain.request()
                 request = if (checkInternetConnection.checkForInternet(context))
            request.newBuilder().header("Cache-Control", "public, max-age=" + 4).build()
        else
            request.newBuilder().header(
                "Cache-Control",
                "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
            ).build()
        chain.proceed(request)
    }

    private val builder =Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }


}