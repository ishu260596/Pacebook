package com.ishwar_arcore.pacebook.views.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class GroupAdapter(private val models: ArrayList<GroupModel>) :
    RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.groups_layout_item, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val model = models[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val groupImage = itemView.findViewById<ImageView>(R.id.ivGroup)
        val groupName = itemView.findViewById<TextView>(R.id.tvGroupName)
        val groupPosts = itemView.findViewById<TextView>(R.id.tvPosts)
        val groupMembers = itemView.findViewById<TextView>(R.id.tvMembers)


        fun setData(model: GroupModel) {
            itemView.apply {
                groupImage.setBackgroundResource(model.imageView)
                groupMembers.text = "members " + model.groupMembers
                groupName.text = model.groupName
                groupPosts.text = "post " + model.posts
            }
        }

    }
}