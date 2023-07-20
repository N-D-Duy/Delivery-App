package com.example.deliveryapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.deliveryapp.R

import com.example.deliveryapp.adapter.WelcomeViewPagerAdapter

import com.example.deliveryapp.databinding.ActivityMainBinding
import com.example.deliveryapp.ui.account.AccountFragment
import com.example.deliveryapp.ui.cart.CartFragment
import com.example.deliveryapp.ui.home.HomeFragment
import com.example.deliveryapp.ui.offer.OffersFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navigationView = binding.bottomNavigation
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setContentView(binding.root)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_offers -> {
                    fragment = OffersFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_cart -> {
                    fragment = CartFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_account -> {
                    fragment = AccountFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }



    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
//     val adapter = WelcomeViewPagerAdapter(supportFragmentManager, lifecycle)


}