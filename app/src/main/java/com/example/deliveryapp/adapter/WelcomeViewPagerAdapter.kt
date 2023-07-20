package com.example.deliveryapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.deliveryapp.ui.fragments.SkipFragment1
import com.example.deliveryapp.ui.fragments.SkipFragment2
import com.example.deliveryapp.ui.fragments.SkipFragment3

class WelcomeViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SkipFragment1()
            1 -> SkipFragment2()
            else -> SkipFragment3()

        }
    }
}