package com.stenleone.fitapp.view.fragment

import com.stenleone.fitapp.R
import com.stenleone.fitapp.view.fragment.base.BaseFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun initViewModel() { }

    override fun initAfterViewCreated() {
        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}
