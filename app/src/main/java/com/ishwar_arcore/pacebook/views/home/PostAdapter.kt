package com.ishwar_arcore.pacebook.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.notification.NotificationAdapter

class PostAdapter() :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_profile_timeline_item_layout_photo, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}