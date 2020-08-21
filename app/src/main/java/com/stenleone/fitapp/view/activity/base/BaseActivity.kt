package com.stenleone.fitapp.view.activity.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.stenleone.fitapp.util.connection_manager.NetworkChangeReceiver
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar.*

abstract class BaseActivity(private val layView: Int) : AppCompatActivity() {

    private val networkChangeReceiver = NetworkChangeReceiver()
    lateinit var navController: NavController

    private fun removeNotifyBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeNotifyBar()
        setContentView(layView)
        navController = Navigation.findNavController(fragmentContainer.view!!)

        if(toolBar != null) {
            setActionBar(toolBar)
            toolBar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
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
}