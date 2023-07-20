package com.example.deliveryapp.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.FragmentSkip3Binding


class SkipFragment3 : Fragment() {
    private lateinit var binding : FragmentSkip3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSkip3Binding.inflate(inflater, container, false)

        binding.tvSkip3.setOnClickListener {

        }


        binding.btnWelcome.setOnClickListener {

        }
        // Inflate the layout for this fragment
        return binding.root
    }


}