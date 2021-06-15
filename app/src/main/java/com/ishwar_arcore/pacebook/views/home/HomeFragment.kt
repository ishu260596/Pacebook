package com.ishwar_arcore.pacebook.views.home

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.mainactivity.MainActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActionBar()?.setTitle("Pacebook")
    }

    @JvmName("getActionBar1")
    private fun getActionBar(): androidx.appcompat.app.ActionBar? {
        return (activity as MainActivity?)?.getSupportActionBar()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}