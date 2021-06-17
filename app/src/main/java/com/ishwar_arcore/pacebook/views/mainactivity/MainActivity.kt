package com.ishwar_arcore.pacebook.views.mainactivity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.addfriends.AddFriendFragment
import com.ishwar_arcore.pacebook.views.group.GroupFragment
import com.ishwar_arcore.pacebook.views.home.HomeFragment
import com.ishwar_arcore.pacebook.views.marketplace.MarketplaceFragment
import com.ishwar_arcore.pacebook.views.menu.MenuFragment
import com.ishwar_arcore.pacebook.views.notification.NotificationFragment
import com.ishwar_arcore.pacebook.views.profile.ProfileFragment
import com.ishwar_arcore.pacebook.views.watch.WatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        setActionBarTitle("Pacebook")
    }

    private fun initViewPager() {

        val list = arrayListOf<Fragment>(
            HomeFragment.newInstance(),
            AddFriendFragment.newInstance(),
            WatchFragment.newInstance(),
            GroupFragment.newInstance(),
            NotificationFragment.newInstance(),
            MenuFragment.newInstance(),
            MarketplaceFragment.newInstance(),
            ProfileFragment.newInstance(),
        )

        viewPagerAdapter = MainViewPagerAdapter(this, list)
        mainViewPager.adapter = viewPagerAdapter

        //val draw = Drawable.createFromResourceStream(R.drawable.icon_home,)

        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.icon = when (position) {
                0 -> ContextCompat.getDrawable(this, R.drawable.ic_one)
                1 -> ContextCompat.getDrawable(this, R.drawable.ic_two)
                2 -> ContextCompat.getDrawable(this, R.drawable.ic_three)
                3 -> ContextCompat.getDrawable(this, R.drawable.ic_four)
                4 -> ContextCompat.getDrawable(this, R.drawable.ic_five)
                5 -> ContextCompat.getDrawable(this, R.drawable.ic_six)
                6 -> ContextCompat.getDrawable(this, R.drawable.ic_seven)
                else -> ContextCompat.getDrawable(this, R.drawable.ic_eight__2_)
            }

        }.attach()
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.setTitle(title)
    }
}