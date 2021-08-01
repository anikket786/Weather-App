package com.anikumar.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.anikumar.weatherapp.R
import com.anikumar.weatherapp.databinding.ActivityMainBinding
import com.anikumar.weatherapp.ui.adapters.FragmentAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val activityMainView = activityMainBinding.root
        setContentView(activityMainView)

        val citiesList = listOf(
            getString(R.string.rio_de_janeiro),
            getString(R.string.beijing),
            getString(R.string.los_angeles),
        )
        with (activityMainBinding) {
            viewPager.adapter = FragmentAdapter(supportFragmentManager, lifecycle, citiesList)

            tabLayout.addTab(tabLayout.newTab().setText(R.string.rio_de_janeiro))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.beijing))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.los_angeles))

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // No action taken.
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // No action taken
                }

            })

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    tabLayout.selectTab(tabLayout.getTabAt(position))
                }
            })
        }
    }
}