package com.coding.sixt.utilitiy
/**
 * this class make a LiveData Check for Checking Network Access on Device
 *
 *  Class return a LiveData Boolean which show you the network status
 *
 * 2022-08-18 2030
 */
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import javax.inject.Inject

 class LiveDataInternetConnections  @Inject constructor (private val connectivityManager: ConnectivityManager): LiveData<Boolean>(){
        constructor(appContext: Application) : this(
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }
        @Inject
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities) {
            val isInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            val isValidated = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            postValue(isInternet && isValidated)
        }
        @Inject
        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }
    @Inject
    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build(), networkCallback)
    }
    @Inject
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
