package com.vmo.ecom.data.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import com.vmo.ecom.R


@Suppress("DEPRECATION")
fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager ?: return false

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
                && (networkInfo.type == ConnectivityManager.TYPE_MOBILE
                || networkInfo.type == ConnectivityManager.TYPE_WIFI)
    } else {
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null
                    && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
        }
    }

    return false
}

fun Activity.setStatusTransparent() {
    window.run {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        statusBarColor = ContextCompat.getColor(context, R.color.background)
    }
}