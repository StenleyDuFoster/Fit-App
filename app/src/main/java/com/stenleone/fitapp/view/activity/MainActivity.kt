package com.stenleone.fitapp.view.activity

import android.os.Bundle
import android.view.View
import com.stenleone.fitapp.R
import com.stenleone.fitapp.util.eventBus.LoadImageEvent
import com.stenleone.fitapp.view.activity.base.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity(R.layout.activity_main) {

    var loadImage = true

    fun navigateToSettings(v: View) {
       if(navController.currentDestination!!.id == R.id.mainFragment) {
           navController.navigate(R.id.to_settingsFragment)
       }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventImage(loadImageEvent: LoadImageEvent) {
        loadImage = loadImageEvent.isLoadImage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}