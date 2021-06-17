package com.ishwar_arcore.pacebook.views.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class MenuAdapter(private val models: ArrayList<MenuModel>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_item_layout, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val model = models[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return  models.size
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.rvIcon)
        val menuName = itemView.findViewById<TextView>(R.id.tvMenuName)
        fun setData(model: MenuModel) {
            itemView.apply {
                imageView.setBackgroundResource(model.icon)
                menuName.text = model.menuName
            }
        }

    }
}

