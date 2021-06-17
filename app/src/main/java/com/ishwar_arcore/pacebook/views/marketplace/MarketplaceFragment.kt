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
    val marketPlaceModels: ArrayList<MarketPlaceModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_marketplace, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        for (i in 0..49) {
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹1000",
                    R.drawable.lipstick
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹500",
                    R.drawable.bangle3
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹700",
                    R.drawable.lahnga2
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹900",
                    R.drawable.ic_lahnga1
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹100",
                    R.drawable.bangles
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹200",
                    R.drawable.saree
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹800",
                    R.drawable.bagsecond
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹900",
                    R.drawable.top5
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹200",
                    R.drawable.top7_1
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹400",
                    R.drawable.watch1
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹3000",
                    R.drawable.watch2
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹500",
                    R.drawable.straitner
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹600",
                    R.drawable.pandent
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹1200",
                    R.drawable.headphone
                )
            )
            marketPlaceModels.add(
                MarketPlaceModel(
                    "₹1500",
                    R.drawable.earing
                )
            )
        }

        rvMarketPlace.layoutManager = GridLayoutManager(context, 2)
        rvMarketPlace.adapter = MarketAdapter(marketPlaceModels, this)
        rvMarketPlace.hasFixedSize()
    }


    companion object {
        fun newInstance() = MarketplaceFragment()
    }
}