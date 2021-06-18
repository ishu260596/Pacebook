package com.ishwar_arcore.pacebook.views.addfriends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.marketplace.MarketPlaceModel


class AddFriendFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildData()
    }

    private fun buildData() {
    }

    companion object {
        fun newInstance() = AddFriendFragment()
    }
}