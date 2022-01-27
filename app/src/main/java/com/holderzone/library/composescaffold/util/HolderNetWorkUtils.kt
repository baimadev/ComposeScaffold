package com.holderzone.library.composescaffold.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.util.Log
import java.net.NetworkInterface
import java.net.SocketException
import java.net.Inet4Address

import java.net.InetAddress

import java.util.Enumeration





object  HolderNetWorkUtils {
    fun getLocalIpAddress(context : Context): String? {
        val networkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected){
            if(networkInfo.type == ConnectivityManager.TYPE_MOBILE){
                try {
                    val en = NetworkInterface.getNetworkInterfaces()
                    while (en.hasMoreElements()) {
                        val intf = en.nextElement()
                        val enumIpAddr = intf.inetAddresses
                        while (enumIpAddr.hasMoreElements()) {
                            val inetAddress = enumIpAddr.nextElement()
                            if (!inetAddress.isLoopbackAddress) {
                                return inetAddress.hostAddress.toString()
                            }
                        }
                    }
                } catch (ex: SocketException) {
                    Log.e("LOG_TAG", ex.toString())
                }
            }else if(networkInfo.type == ConnectivityManager.TYPE_WIFI){
                val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                return intIP2StringIP(wifiInfo.ipAddress)
            }else{
                return getLocalIp()
            }
        }
        return null
    }
    private fun intIP2StringIP(ip: Int): String? {
        return (ip and 0xFF).toString() + "." +
                (ip shr 8 and 0xFF) + "." +
                (ip shr 16 and 0xFF) + "." +
                (ip shr 24 and 0xFF)
    }
    private fun getLocalIp(): String? {
        try {
            val en = NetworkInterface
                .getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf
                    .inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress
                        && inetAddress is Inet4Address
                    ) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
        }
        return "0.0.0.0"
    }

}