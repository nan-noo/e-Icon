package com.example.datausage.model

import android.app.usage.NetworkStatsManager
import android.net.ConnectivityManager
import android.net.TrafficStats
import android.telephony.TelephonyManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _dataUsage = MutableLiveData<Long>()
    val dataUsage: LiveData<Long> = _dataUsage

    init {
        resetData()
    }

    // To get all Mobile Rx bytes
    //Log.e("bytes recvd", "" + TrafficStats.getMobileRxBytes())
    // To get Total Rx bytes
    //Log.e("Total", "Bytes received: " + TrafficStats.getTotalRxBytes())

    fun getTotalDataUsage(){
        //_dataUsage.value = NetworkStatsManager.querySummary(ConnectivityManager, TelephonyManager.getSubscriberId(), 0, System.currentTimeMillis())
        _dataUsage.value = TrafficStats.getTotalRxBytes()
    }

    private fun resetData() {
        _dataUsage.value = 0
    }
}