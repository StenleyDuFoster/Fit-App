package com.stenleone.fitapp.view.activity.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.stenleone.fitapp.util.connection_manager.NetworkChangeReceiver

abstract class BaseActivity(val layView: Int) : AppCompatActivity() {

    private val networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeNotifyBar()
        setContentView(layView)
    }

    override fun onStart() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeReceiver)
        super.onStop()
    }

    private fun removeNotifyBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}