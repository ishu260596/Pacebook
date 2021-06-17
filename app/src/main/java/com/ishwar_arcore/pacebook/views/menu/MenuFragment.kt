package com.ishwar_arcore.pacebook.views.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class MenuFragment : Fragment() {
    val models: ArrayList<MenuModel> = ArrayList()
    var rvMenus: RecyclerView? = null
    var menuAdapter: MenuAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuAdapter = MenuAdapter(models)
        rvMenus = view.findViewById(R.id.rvMenus)
        rvMenus?.hasFixedSize()
        buildData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        rvMenus?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = menuAdapter
        }

    }

    private fun buildData() {
        models.add(MenuModel(R.drawable.ic_one, "Marketplace"))
        models.add(MenuModel(R.drawable.ic_one, "Memories"))
        models.add(MenuModel(R.drawable.ic_one, "Pages"))
        models.add(MenuModel(R.drawable.ic_one, "Gaming"))
        models.add(MenuModel(R.drawable.ic_one, "Covid 19 Info"))
        models.add(MenuModel(R.drawable.ic_one, "Find Friends"))
        models.add(MenuModel(R.drawable.ic_one, "Groups"))
        models.add(MenuModel(R.drawable.ic_one, "Reels"))
        models.add(MenuModel(R.drawable.ic_one, "Saved"))
        models.add(MenuModel(R.drawable.ic_one, "Events"))
        models.add(MenuModel(R.drawable.ic_one, "Jobs"))
    }

    companion object {
        fun newInstance() = MenuFragment()
    }
}