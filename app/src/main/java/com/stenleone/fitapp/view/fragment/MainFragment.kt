package com.stenleone.fitapp.view.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.data.ItemFitApp
import com.stenleone.fitapp.model.view_model.ListViewModel
import com.stenleone.fitapp.util.anim.CustomAnimate
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.util.eventBus.IsLoadImageEventBus
import com.stenleone.fitapp.view.activity.base.BaseActivity
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import com.stenleone.fitapp.view.recycler.PlanRecyclerAdapter
import com.stenleone.fitapp.view.recycler.callback.CallBackFromRecyclerToFragment
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.loading_lay.*

class MainFragment : BaseFragment(R.layout.fragment_main), CallBackFromRecyclerToFragment {

    private var itemsList = ArrayList<ItemFitApp>()

    override fun initAfterViewCreated() {

        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(false)
        getModelList()
        initRecycler()

        IsLoadImageEventBus.getObservable().subscribe{
            (recycler.adapter as PlanRecyclerAdapter).isLoadImage = (it as Boolean)
        }
        (activity!! as BaseActivity).networkChangeReceiver.setRunnableCode({
            getModelList()
        })
    }

    private fun getModelList() {
        if(itemsList.size < 1) {
            (viewModel as ListViewModel).getListFitPlan()
            CustomAnimate.alphaFadeIn(activity!!.loadLay)
            activity!!.loadStatus.text = getString(R.string.loading_content)
        }
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = PlanRecyclerAdapter()
    }

    override fun initModel() {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        (viewModel as ListViewModel).getList().observe(viewLifecycleOwner, { list ->

            animLoad()
            itemsList = ArrayList(list)
            (recycler.adapter as PlanRecyclerAdapter).setAdapterParams(
                itemsList,
                this as CallBackFromRecyclerToFragment
            )
            this.recycler.adapter?.notifyDataSetChanged()
        })

        viewModel.getError().observe(viewLifecycleOwner, { throwable ->

            makeToast(throwable.toString())
        })
    }

    private fun animLoad() {
        CustomAnimate.alphaFadeOut(activity!!.loadLay)
        view!!.setBackgroundColor(resources.getColor(R.color.colorBackground))
    }

    override fun itemClick(position: Int) {

        val bundle = Bundle()
        val arrayStringItem: ArrayList<String> = ArrayList(
            listOf(
                itemsList[position].id.toString(),
                itemsList[position].athleteFirstName,
                itemsList[position].athleteLastName,
                itemsList[position].name,
                itemsList[position].imageSmallUrl
            )
        )

        bundle.putStringArrayList("item", arrayStringItem)
        navController.navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }
}