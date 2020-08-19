package com.stenleone.fitapp.view.activity

import android.os.Bundle
import com.stenleone.fitapp.R
import com.stenleone.fitapp.view.activity.base.BaseActivity
import com.stenleone.fitapp.view.fragment.LoginFragment

class loginActivity : BaseActivity(R.layout.activity_login) {

    val loginFragment = LoginFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}