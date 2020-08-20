package com.stenleone.fitapp.view.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.view_model.LoginViewModel
import com.stenleone.fitapp.util.anim.CustomAnimate
import com.stenleone.fitapp.util.constant.ApiFitPlanConstant
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    override fun initAfterViewCreated() {

        textInputEmailEditText.setText(ApiFitPlanConstant.USER_LOGIN)
        textInputPasswordEditText.setText(ApiFitPlanConstant.USER_PASSWORD)

        loginButton.setOnClickListener { v ->

            (viewModel as LoginViewModel).logInFitPlan(
                textInputEmailEditText.text.toString(),
                textInputPasswordEditText.text.toString())

            setViewWaitMode(false)
        }
    }

    override fun initViewModel() {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        (viewModel as LoginViewModel).getUser().observe(viewLifecycleOwner, Observer { user ->

            navController.navigate(R.id.action_loginFragment_to_mainActivity)
            activity?.finish()
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer { throwable ->

            makeToast(throwable.toString())
            setViewWaitMode(true)
        })
    }

    private fun setViewWaitMode(bool : Boolean) {

        textInputEmailEditText.isClickable = bool
        textInputPasswordEditText.isClickable = bool
        loginButton.isClickable = bool

        if(bool) {
            CustomAnimate.alphaFadeOut(activity!!.loadLay)
        } else {
            CustomAnimate.alphaFadeIn(activity!!.loadLay)
        }
    }
}