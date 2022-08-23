
/**
 * function of checkForInternet in this class check Internet connection
 * context is the enter param  and return a Boolean value which shows Internet Status
 *  2022-08-19  16:00
 */


package com.coding.sixt.utilitiy


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.coding.sixt.model.ICheckInternetConnection


class CheckInternetConnection : ICheckInternetConnection {
      override fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         val network = connectivityManager.activeNetwork ?: return false
         val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
         return when {
             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
             activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
             else -> false
         }
     }}