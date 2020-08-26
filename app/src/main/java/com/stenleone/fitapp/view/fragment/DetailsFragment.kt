package com.stenleone.fitapp.view.fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.view_model.DetailsViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.util.eventBus.IsLoadImageEventBus
import com.stenleone.fitapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    override fun initAfterViewCreated() {

        activity!!.actionBar!!.setDisplayHomeAsUpEnabled(true)
        val saveContent = arguments!!.getStringArrayList("item")

        text_1.text = (saveContent?.get(1) + " " + saveContent?.get(2))
        text_2.text = saveContent?.get(3)
        text_3.text = ("loading...")

        IsLoadImageEventBus.getObservable().subscribe {
            if (!(it as Boolean)) {
                image.visibility = View.GONE
            }
        }

        Glide
            .with(context!!)
            .load(saveContent!![4])
            .centerCrop()
            .into(image)
        (viewModel as DetailsViewModel).getItemDetails(saveContent[0].toInt())
    }

    override fun initModel() {

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        (viewModel as DetailsViewModel).getItem().observe(viewLifecycleOwner, { item ->
            if (item != null)
                text_3.text = item.result.description
        })

        viewModel.getError().observe(viewLifecycleOwner, { error ->
            makeToast(error)
        })
    }
}