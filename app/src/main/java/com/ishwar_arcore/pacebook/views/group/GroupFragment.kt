package com.ishwar_arcore.pacebook.views.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R

class GroupFragment : Fragment() {
    val models: ArrayList<GroupModel> = ArrayList()
    var rvGroups: RecyclerView? = null
    var groupAdapter: GroupAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupAdapter = GroupAdapter(models)
        buildData()
        setRecyclerView(view)
    }

    private fun buildData() {
        models.add(GroupModel(R.drawable.masaischool, "Masai School", "1200k", "100"))
        models.add(GroupModel(R.drawable.android, "Android Developer Spartans", "1900k", "800"))
        models.add(GroupModel(R.drawable.ai, "Data Structure and Algorithms ", "1500k", "100k"))
        models.add(GroupModel(R.drawable.yoga, "Yoga and Exercise", "1800k", "890"))
        models.add(GroupModel(R.drawable.covidreleif, "Covid Relief", "12100k", "200k"))
        models.add(
            GroupModel(
                R.drawable.machine,
                "Technology and Machine Learning",
                "1600k",
                "10k"
            )
        )
        models.add(GroupModel(R.drawable.masaischool, "Masai School", "1200k", "100"))
        models.add(GroupModel(R.drawable.android, "Android Developer Spartans", "1900k", "800"))
        models.add(GroupModel(R.drawable.ai, "Data Structure and Algorithms ", "1500k", "100k"))
        models.add(GroupModel(R.drawable.yoga, "Yoga and Exercise", "1800k", "890"))
        models.add(GroupModel(R.drawable.covidreleif, "Covid Relief", "12100k", "200k"))

    }

    private fun setRecyclerView(view: View) {
        rvGroups = view.findViewById(R.id.rvMarketPlace)
        rvGroups?.hasFixedSize()
        rvGroups?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
        }

    }

    companion object {
        fun newInstance() = GroupFragment()
    }

}