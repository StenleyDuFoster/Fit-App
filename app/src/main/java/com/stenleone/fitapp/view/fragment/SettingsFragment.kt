package com.stenleone.fitapp.view.fragment

import com.stenleone.fitapp.R
import com.stenleone.fitapp.util.shared_preferences.SharedPreferencesManager
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val sharedPreferences = SharedPreferencesManager()

    override fun initModel() { }

    override fun initAfterViewCreated() {
        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(true)

        logOut.setOnClickListener {
            sharedPreferences.setToken(null)
            navController.navigate(R.id.action_settingsFragment_to_loginActivity)
            activity!!.finish()
        }
    }
}
