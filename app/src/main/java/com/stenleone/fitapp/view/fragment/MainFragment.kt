package com.stenleone.fitapp.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.view_model.ListViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.view.fragment.base.BaseFragment

class MainFragment : BaseFragment(R.layout.fragment_main) {

    override fun initAfterViewCreated() {

        (viewModel as ListViewModel).getListFitPlan("")
    }

    override fun initViewModel() {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        (viewModel as ListViewModel).getList().observe(viewLifecycleOwner, Observer { list ->

            makeToast(list.size.toString())
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer { throwable ->

            makeToast(throwable.toString())
        })
    }
}