package com.anikumar.weatherapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anikumar.weatherapp.ui.WeatherForecastFragment

class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
    private val tabList: List<String>
) : FragmentStateAdapter(fragmentManager, lifeCycle) {

    override fun getItemCount(): Int {
        return tabList.size
    }

    override fun createFragment(position: Int): Fragment {
        return WeatherForecastFragment.newInstance(tabList[position])
    }
}