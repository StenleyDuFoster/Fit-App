package com.stenleone.fitapp.view.fragment

import android.app.Activity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.data.ItemFitApp
import com.stenleone.fitapp.model.view_model.ListViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.util.eventBus.LoadImageEvent
import com.stenleone.fitapp.view.activity.MainActivity
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import com.stenleone.fitapp.view.recycler.RecyclerAdapter
import com.stenleone.fitapp.view.recycler.callback.CallBackFromRecyclerToFragment
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.fragment_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainFragment : BaseFragment(R.layout.fragment_main), CallBackFromRecyclerToFragment {

    private var itemsList = ArrayList<ItemFitApp>()

    override fun initAfterViewCreated() {

        if(itemsList.size < 1) {
            (viewModel as ListViewModel).getListFitPlan()
        }
        recycler.layoutManager = LinearLayoutManager(context)
        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(false)
        recycler.adapter = RecyclerAdapter(
            itemsList,
            this as CallBackFromRecyclerToFragment,
            (activity!! as MainActivity).loadImage)
    }

    override fun initModel() {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        (viewModel as ListViewModel).getList().observe(viewLifecycleOwner, { list ->

            itemsList = ArrayList(list)
            recycler.adapter = RecyclerAdapter(
                itemsList,
                this as CallBackFromRecyclerToFragment,
                (activity!! as MainActivity).loadImage)
        })

        viewModel.getError().observe(viewLifecycleOwner, { throwable ->

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