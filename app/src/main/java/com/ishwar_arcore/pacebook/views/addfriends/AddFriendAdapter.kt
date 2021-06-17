package com.ishwar_arcore.pacebook.views.addfriends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class AddFriendAdapter() :
    RecyclerView.Adapter<AddFriendAdapter.AddFriendlierHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendlierHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_add_friend, parent, false)
        return AddFriendlierHolder(view)
    }

    override fun onBindViewHolder(holder: AddFriendlierHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class AddFriendlierHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}