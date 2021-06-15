package com.ishwar_arcore.pacebook.views.marketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ishwar_arcore.pacebook.R
import kotlinx.android.synthetic.main.fragment_marketplace.*

class MarketplaceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marketplace, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val models: ArrayList<Model> = ArrayList()

        for (i in 0..49) {
            models.add(Model("₹1000", R.drawable.lipstick))
            models.add(Model("₹500", R.drawable.bangle3))
            models.add(Model("₹700", R.drawable.lahnga2))
            models.add(Model("₹900", R.drawable.ic_lahnga1))
            models.add(Model("₹100", R.drawable.bangles))
            models.add(Model("₹200", R.drawable.saree))
            models.add(Model("₹800", R.drawable.bagsecond))
            models.add(Model("₹900", R.drawable.top5))
            models.add(Model("₹200", R.drawable.top7_1))
            models.add(Model("₹400", R.drawable.watch1))
            models.add(Model("₹3000", R.drawable.watch2))
            models.add(Model("₹500", R.drawable.straitner))
            models.add(Model("₹600", R.drawable.pandent))
            models.add(Model("₹1200", R.drawable.headphone))
            models.add(Model("₹1500", R.drawable.earing))
        }
        rvGroups.layoutManager = GridLayoutManager(context,2)
        rvGroups.adapter = MarketAdapter(models, this)
    }





    companion object {
        fun newInstance() = MarketplaceFragment()
    }
}