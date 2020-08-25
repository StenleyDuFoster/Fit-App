package com.stenleone.fitapp.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.fitapp.R
import com.stenleone.fitapp.util.eventBus.LoadImageEvent
import com.stenleone.fitapp.view.activity.base.BaseActivity
import kotlinx.android.synthetic.main.app_bar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(R.layout.activity_main) {

    var loadImage = true

    @Subscribe
    fun eventImage(loadImageEvent: LoadImageEvent) {
        EventBus.getDefault().postSticky(loadImageEvent.isLoadImage)
        loadImage = loadImageEvent.isLoadImage
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

        settingsButton.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                if(navController.currentDestination!!.id != R.id.settingsFragment) {
                    navController.navigate(R.id.to_settingsFragment)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}