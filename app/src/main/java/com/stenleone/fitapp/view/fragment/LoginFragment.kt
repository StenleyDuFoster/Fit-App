package com.stenleone.fitapp.view.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding3.view.clicks

import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.view_model.LoginViewModel
import com.stenleone.fitapp.util.anim.CustomAnimate
import com.stenleone.fitapp.util.constant.ApiFitPlanConstant
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.loading_lay.*
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    @SuppressLint("CheckResult")
    override fun initAfterViewCreated() {

        textInputEmailEditText.setText(ApiFitPlanConstant.USER_LOGIN)
        textInputPasswordEditText.setText(ApiFitPlanConstant.USER_PASSWORD)

        loginButton.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                (viewModel as LoginViewModel).logInFitPlan(
                    textInputEmailEditText.text.toString(),
                    textInputPasswordEditText.text.toString()
                )
                setViewWaitMode(false)
            }
        activity!!.loadStatus.text = getString(R.string.auth_message)
    }

    override fun initModel() {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        (viewModel as LoginViewModel).getUser().observe(viewLifecycleOwner, {

            navController.navigate(R.id.action_loginFragment_to_mainActivity)
            activity?.finish()
        })

        viewModel.getError().observe(viewLifecycleOwner, { throwable ->

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