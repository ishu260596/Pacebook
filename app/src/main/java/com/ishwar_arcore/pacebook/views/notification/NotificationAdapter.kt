package com.ishwar_arcore.pacebook.views.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.profile.ProfileTimelineAdapter

class NotificationAdapter() :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_item_layout, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}