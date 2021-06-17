package com.ishwar_arcore.pacebook.views.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class ProfileTimelineAdapter() :
    RecyclerView.Adapter<ProfileTimelineAdapter.ProfileTimelineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileTimelineViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_profile_timeline_item_layout_photo, parent, false)
        return ProfileTimelineViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileTimelineViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ProfileTimelineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}