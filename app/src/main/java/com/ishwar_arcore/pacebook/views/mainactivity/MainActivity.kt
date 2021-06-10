package com.ishwar_arcore.pacebook.views.mainactivity


import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ishwar_arcore.pacebook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
    }

    private fun initViewPager() {
        viewPagerAdapter = MainViewPagerAdapter(this)
        mainViewPager.adapter = viewPagerAdapter

        TabLayoutMediator(mainTabLayout,mainViewPager){ tab, position ->
            tab.icon = when(position){
                0 -> R.drawable.icon_home.toDrawable()
                1 -> R.drawable.icon_video.toDrawable()
                2 -> R.drawable.icon_shop.toDrawable()
                3 -> R.drawable.icon_profile.toDrawable()
                4 -> R.drawable.icon_notification.toDrawable()
                5 -> R.drawable.icon_menu.toDrawable()
                else -> R.drawable.icon_home.toDrawable()
            }
        }.attach()
    }
}