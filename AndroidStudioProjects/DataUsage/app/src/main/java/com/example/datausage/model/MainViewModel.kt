package com.example.datausage.model

import android.net.TrafficStats
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlin.math.round


class MainViewModel: ViewModel() {
    private val _totalDataUsageR = MutableLiveData<Float>()
    private val _totalDataUsageT = MutableLiveData<Float>()
    val totalDataUsage: LiveData<Float> = _totalDataUsageR

    private val _mobileDataUsageR = MutableLiveData<Float>()
    private val _mobileDataUsageT = MutableLiveData<Float>()

    private val _wifiDataUsageR = MutableLiveData<Float>()
    private val _wifiDataUsageT = MutableLiveData<Float>()

    private val _entries = ArrayList<PieEntry>()
    val entries = _entries

    private val _colors: ArrayList<Int> = ArrayList()
    val colors = _colors

    init {
        setChartData()
    }

    fun setChartData() {
        getDataUsage()

        _entries.clear()
        _colors.clear()

        _entries.add(PieEntry(_mobileDataUsageR.value!!, "mobile Rx"))
        _entries.add(PieEntry(_wifiDataUsageR.value!!, "wifi Rx"))
        _entries.add(PieEntry(_mobileDataUsageT.value!!, "mobile Tx"))
        _entries.add(PieEntry(_wifiDataUsageT.value!!, "wifi Tx"))

        // colors
        for (c in ColorTemplate.LIBERTY_COLORS) _colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) _colors.add(c)
    }

    // TrafficStats
    /*
    TX and RX are abbreviations for Transmit and Receive, respectively.
    Note that these metrics are referenced to the server being monitored;
    Transmit FROM this server, and Receive TO this server.
    */

    private fun getDataUsage(){
        _totalDataUsageR.value = round(TrafficStats.getTotalRxBytes() * 100f) / 100000.0f
        _mobileDataUsageR.value = round(TrafficStats.getMobileRxBytes() * 100f) / 100000.0f
        _totalDataUsageT.value = round(TrafficStats.getTotalTxBytes() * 100f) / 100000.0f
        _mobileDataUsageT.value = round(TrafficStats.getMobileTxBytes() * 100f) / 100000.0f
        _wifiDataUsageR.value = _totalDataUsageR.value!! - _mobileDataUsageR.value!!
        _wifiDataUsageT.value = _totalDataUsageT.value!! - _mobileDataUsageT.value!!
    }

}