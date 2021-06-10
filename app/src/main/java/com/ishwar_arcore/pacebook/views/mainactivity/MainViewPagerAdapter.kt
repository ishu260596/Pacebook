package com.ishwar_arcore.pacebook.views.mainactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.appbar.AppBarLayout
import com.ishwar_arcore.pacebook.views.home.HomeFragment
import com.ishwar_arcore.pacebook.views.marketplace.MarketplaceFragment
import com.ishwar_arcore.pacebook.views.menu.MenuFragment
import com.ishwar_arcore.pacebook.views.notification.NotificationFragment
import com.ishwar_arcore.pacebook.views.profile.ProfileFragment
import com.ishwar_arcore.pacebook.views.watch.WatchFragment

class MainViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> WatchFragment.newInstance()
            2 -> MarketplaceFragment.newInstance()
            3 -> ProfileFragment.newInstance()
            4 -> NotificationFragment.newInstance()
            5 -> MenuFragment.newInstance()
            else -> HomeFragment.newInstance()
        }
    }

}