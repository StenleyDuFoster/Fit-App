package com.stenleone.fitapp.view.fragment

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.data.ItemFitApp
import com.stenleone.fitapp.model.view_model.ListViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import com.stenleone.fitapp.view.recycler.RecyclerAdapter
import com.stenleone.fitapp.view.recycler.callback.CallBackFromRecyclerToFragment

import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment(R.layout.fragment_main), CallBackFromRecyclerToFragment {

    private var itemsList = ArrayList<ItemFitApp>()

    override fun initAfterViewCreated() {

        if(itemsList.size < 1) {
            (viewModel as ListViewModel).getListFitPlan()
        }

        recycler.layoutManager = LinearLayoutManager(context)
        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun initModel() {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        (viewModel as ListViewModel).getList().observe(viewLifecycleOwner, Observer { list ->

            itemsList = ArrayList(list)
            recycler.adapter = RecyclerAdapter(itemsList, this as CallBackFromRecyclerToFragment)
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer { throwable ->

            makeToast(throwable.toString())
        })
    }

    override fun itemClick(position: Int) {

        val bundle = Bundle()
        val arrayStringItem: ArrayList<String> = ArrayList(
            listOf(
            itemsList[position].id.toString(),
            itemsList[position].athleteFirstName,
            itemsList[position].athleteLastName,
            itemsList[position].name,
            itemsList[position].imageSmallUrl)
        )

        bundle.putStringArrayList("item", arrayStringItem)
        navController.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }
}