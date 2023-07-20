package com.example.deliveryapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.FragmentSkip1Binding

class SkipFragment1 : Fragment() {

    private lateinit var binding : FragmentSkip1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSkip1Binding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.tvSkip.setOnClickListener {


        }
        return binding.root
    }


}