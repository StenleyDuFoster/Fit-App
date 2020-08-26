package com.stenleone.fitapp.util.connection_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.stenleone.fitapp.R
import com.stenleone.fitapp.util.easyToast.makeToast

class NetworkChangeReceiver : BroadcastReceiver() {

    private var doAfterConnectionWillResume: Runnable? = null

    private var oldNetworkState = true
    private var newNetworkState = true

    override fun onReceive(context: Context?, intent: Intent) {

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (cm.activeNetworkInfo == null) {
            newNetworkState = false

            if (newNetworkState != oldNetworkState) {
                makeToast(context.getString(R.string.connection_lost))
            }

            oldNetworkState = newNetworkState
        } else {
            newNetworkState = true

            if (newNetworkState != oldNetworkState) {
                makeToast(context.getString(R.string.connection_restored))
                doAfterConnectionWillResume?.run()
            }

            oldNetworkState = newNetworkState
        }
    }

    fun setRunnableCode(doAfterConnectionWillResume: Runnable) {

        this.doAfterConnectionWillResume = doAfterConnectionWillResume
    }

    fun removeRunnableCode() {

        doAfterConnectionWillResume = null
    }
}