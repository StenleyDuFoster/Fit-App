package com.stenleone.fitapp.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stenleone.fitapp.R
import com.stenleone.fitapp.koin.application.App
import com.stenleone.fitapp.model.data.ItemFitApp
import com.stenleone.fitapp.view.recycler.callback.CallBackFromRecyclerToFragment

class RecyclerAdapter(arrayItems: ArrayList<ItemFitApp>, listener: CallBackFromRecyclerToFragment?) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val arrayItems: ArrayList<ItemFitApp> = arrayItems
    private var callBackFromRecyclerToFragment: CallBackFromRecyclerToFragment? = listener

    private lateinit var background: ImageView
    private lateinit var firstText: TextView
    private lateinit var secondText: TextView

    inner class ViewHolder(itemView: View, listener: CallBackFromRecyclerToFragment?) :
        RecyclerView.ViewHolder(itemView) {

        init {
            background = itemView.findViewById(R.id.imageBackground)
            firstText = itemView.findViewById(R.id.firstText)
            secondText = itemView.findViewById(R.id.secondText)

            itemView.setOnClickListener {
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
        return ViewHolder(v, callBackFromRecyclerToFragment)
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapter.ViewHolder,
        position: Int
    ) {
        val currentItem: ItemFitApp = arrayItems[position]

        firstText.setText(currentItem.athleteFirstName + " " + currentItem.athleteLastName)
        secondText.setText(currentItem.name)

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