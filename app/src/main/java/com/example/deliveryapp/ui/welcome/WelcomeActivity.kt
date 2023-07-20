package com.example.deliveryapp.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.deliveryapp.R
import com.example.deliveryapp.adapter.WelcomeViewPagerAdapter
import com.example.deliveryapp.databinding.ActivityWelcomeBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)

        val viewPager = findViewById<ViewPager2>(R.id.welcome_viewpager)
        val adapter = WelcomeViewPagerAdapter(this)
        val dotsIndicator = findViewById<DotsIndicator>(R.id.welcome_indicator)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)

    }
}