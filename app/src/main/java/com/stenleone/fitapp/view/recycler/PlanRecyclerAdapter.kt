package com.stenleone.fitapp.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.fitapp.R
import com.stenleone.fitapp.koin.application.App
import com.stenleone.fitapp.model.data.ItemFitApp
import com.stenleone.fitapp.view.recycler.callback.CallBackFromRecyclerToFragment
import java.util.concurrent.TimeUnit

class PlanRecyclerAdapter : RecyclerView.Adapter<PlanRecyclerAdapter.ViewHolder>() {

    private var arrayItems: ArrayList<ItemFitApp> = ArrayList()
    private lateinit var listener: CallBackFromRecyclerToFragment
    private var isLoadImage: Boolean = true

    private lateinit var background: ImageView
    private lateinit var firstText: TextView
    private lateinit var secondText: TextView

    fun setAdapterParams(
        items: ArrayList<ItemFitApp>,
        listener: CallBackFromRecyclerToFragment,
        isLoadImageEvent: Boolean) {

        this.arrayItems = items
        this.listener = listener
        this.isLoadImage = isLoadImageEvent
    }

    inner class ViewHolder(itemView: View, listener: CallBackFromRecyclerToFragment?) :
        RecyclerView.ViewHolder(itemView) {

        init {
            background = itemView.findViewById(R.id.imageBackground)
            firstText = itemView.findViewById(R.id.firstText)
            secondText = itemView.findViewById(R.id.secondText)

            itemView.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.itemClick(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val lay: Int = R.layout.item_lay

        val v: View = LayoutInflater.from(parent.context).inflate(lay, parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(
        holder: PlanRecyclerAdapter.ViewHolder,
        position: Int
    ) {
        val currentItem: ItemFitApp = arrayItems[position]

        firstText.text = (currentItem.athleteFirstName + " " + currentItem.athleteLastName)
        secondText.text = currentItem.name

        if(!isLoadImage) {
            background.visibility = View.GONE
        }
            Glide
                .with(App.contextComponent)
                .load(currentItem.imageSmallUrl)
                .centerCrop()
                .into(background)

    }

    override fun getItemCount(): Int {
        return arrayItems.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}