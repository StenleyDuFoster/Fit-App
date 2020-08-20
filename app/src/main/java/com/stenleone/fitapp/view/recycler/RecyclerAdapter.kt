package com.stenleone.fitapp.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stenleone.fitapp.R
import com.stenleone.fitapp.model.data.ItemFitApp
import com.stenleone.fitapp.view.recycler.callback.CallBackFromRecyclerToFragment

class RecyclerAdapter(exampleList: ArrayList<ItemFitApp>, var type: Int, listener: CallBackFromRecyclerToFragment?) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val mExampleList: ArrayList<ItemFitApp> = exampleList
    private var mListener: CallBackFromRecyclerToFragment? = listener

    inner class ViewHolder(itemView: View, listener: CallBackFromRecyclerToFragment?) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                       listener
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val lay: Int = 1

        val v: View = LayoutInflater.from(parent.context).inflate(lay, parent, false)
        return ViewHolder(v, mListener)
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapter.ViewHolder,
        position: Int
    ) {
        val currentItem: ItemFitApp = mExampleList[position]

    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    override fun getItemViewType(position: Int): Int {
        return type
    }
}