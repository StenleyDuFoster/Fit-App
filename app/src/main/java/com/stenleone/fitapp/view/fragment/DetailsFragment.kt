package com.stenleone.fitapp.view.fragment

import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.view_model.DetailsViewModel
import com.stenleone.fitapp.model.view_model.ListViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment(R.layout.fragment_details) {


    override fun initAfterViewCreated() {

        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(true)

        val saveContent = arguments!!.getStringArrayList("item")

        text_1.setText(saveContent?.get(1) + " " + saveContent?.get(2))
        text_2.setText(saveContent?.get(3))
        text_3.setText("loading...")

        Glide
            .with(context!!)
            .load(saveContent!![4])
            .centerCrop()
            .into(image)
        (viewModel as DetailsViewModel).getItemDetails(saveContent[0].toInt())
    }

    override fun initViewModel() {

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        (viewModel as DetailsViewModel).getItem().observe(viewLifecycleOwner, Observer { item ->
            if(item != null)
                text_3.setText(item.result.description)
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer { error ->
            makeToast(error)
        })
    }
}