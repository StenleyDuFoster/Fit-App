package com.stenleone.fitapp.view.activity

import android.view.View
import com.stenleone.fitapp.R
import com.stenleone.fitapp.view.activity.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    fun navigateToSettings(v: View) {
        navController.navigate(R.id.to_settingsFragment)
    }
}