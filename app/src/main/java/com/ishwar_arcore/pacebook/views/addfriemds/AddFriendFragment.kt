package com.ishwar_arcore.pacebook.views.addfriemds

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.watch.WatchFragment
import kotlinx.android.synthetic.main.fragment_add_friend.*


class AddFriendFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageButton.setOnClickListener {
            val intent= Intent(activity,SearchActivity::class.java)
            startActivity(intent)
        }


    }


    companion object {
        fun newInstance() = AddFriendFragment()
    }
}