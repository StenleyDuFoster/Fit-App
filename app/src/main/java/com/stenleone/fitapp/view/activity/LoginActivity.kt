package com.stenleone.fitapp.view.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.stenleone.fitapp.R
import com.stenleone.fitapp.util.anim.CustomAnimate
import com.stenleone.fitapp.util.shared_preferences.SharedPreferencesManager
import com.stenleone.fitapp.view.activity.base.BaseActivity
import com.stenleone.fitapp.view.fragment.LoginFragment
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(R.layout.activity_login) {

    private val sharedPreferences = SharedPreferencesManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentContainer.view!!.alpha = 0f
        navController = Navigation.findNavController(fragmentContainer.view!!)

        if(sharedPreferences.getToken() == null) {
            CustomAnimate.alphaFadeIn(fragmentContainer.view!!)
        } else {
            navController.navigate(R.id.action_loginFragment_to_mainActivity)
            finish()
        }
    }
}