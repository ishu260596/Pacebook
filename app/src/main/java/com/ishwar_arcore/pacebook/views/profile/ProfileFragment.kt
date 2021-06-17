package com.ishwar_arcore.pacebook.views.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.addfriemds.SearchActivity
import kotlinx.android.synthetic.main.fragment_add_friend.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvSeeMore.setOnClickListener {
            val intent= Intent(activity, AddInformationActivity::class.java)
            startActivity(intent)
        }


    }


    companion object {
        fun newInstance() = ProfileFragment()
    }
}