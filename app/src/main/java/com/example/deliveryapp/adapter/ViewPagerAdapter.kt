package com.example.deliveryapp.adapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.deliveryapp.ui.fragments.SkipFragment1
import com.example.deliveryapp.ui.fragments.SkipFragment2
import com.example.deliveryapp.ui.fragments.SkipFragment3

class ViewPagerAdapter(fragmentManager: androidx.fragment.app.FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {SkipFragment1()}
            1 -> {
                SkipFragment2()
            }
            else -> {SkipFragment3()}

        }
    }
}