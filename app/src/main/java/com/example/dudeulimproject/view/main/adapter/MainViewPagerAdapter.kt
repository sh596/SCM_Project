package com.example.dudeulimproject.view.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dudeulimproject.view.main.fragment.InterViewFragment
import com.example.dudeulimproject.view.main.fragment.MainFragment
import com.example.dudeulimproject.view.main.fragment.ProfileFragment
import com.example.dudeulimproject.view.main.fragment.SearchFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> SearchFragment()
            2 -> InterViewFragment()
            3 -> ProfileFragment()
            else -> MainFragment()
        }
    }
}