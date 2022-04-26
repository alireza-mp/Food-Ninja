package com.digimoplus.foodninja.domain.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.*
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.util.Log
import androidx.lifecycle.LiveData

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Handler
import javax.inject.Inject


class ConnectionLiveData
@Inject constructor(
    private val context: Context,
    private val onlineChecker: OnlineChecker
) : LiveData<Boolean>() {


    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()


    private fun checkValidNetworks() {
        postValue(validNetworks.size > 0)
    }

    override fun onActive() {
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)

        CoroutineScope(Dispatchers.IO).launch {
            if (!onlineChecker.isOnline) {
                CoroutineScope(Dispatchers.Main).launch {
                    postValue(false)
                }
            }
        }
    }

    override fun onInactive() {
        cm.unregisterNetworkCallback(networkCallback)
        validNetworks.clear()
    }

    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

        /*
          Called when a network is detected. If that network has internet, save it in the Set.
          Source: https://developer.android.com/reference/android/net/ConnectivityManager.NetworkCallback#onAvailable(android.net.Network)
         */
        override fun onAvailable(network: Network) {
            val networkCapabilities = cm.getNetworkCapabilities(network)
            val hasInternetCapability = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            if (hasInternetCapability == true) {
                // check if this network actually has internet
                CoroutineScope(Dispatchers.IO).launch {
                    val hasInternet = onlineChecker.isOnline
                    if (hasInternet) {
                        withContext(Dispatchers.Main) {
                            validNetworks.add(network)
                            checkValidNetworks()
                        }
                    }
                }
            }
        }

        /*
          If the callback was registered with registerNetworkCallback() it will be called for each network which no longer satisfies the criteria of the callback.
          Source: https://developer.android.com/reference/android/net/ConnectivityManager.NetworkCallback#onLost(android.net.Network)
         */
        override fun onLost(network: Network) {
            validNetworks.remove(network)
            checkValidNetworks()
        }

    }

}