package com.stenleone.fitapp.view.activity

import android.os.Bundle
import com.stenleone.fitapp.R
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.view.activity.base.BaseActivity
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBar(toolBar)
    }
}