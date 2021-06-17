package com.ishwar_arcore.pacebook.views.watch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class WatchAdapter() : RecyclerView.Adapter<WatchAdapter.WatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_profile_timeline_item_layout_photo, parent, false)
        return WatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    inner class WatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}