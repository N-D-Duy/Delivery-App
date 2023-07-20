package com.example.deliveryapp.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.FragmentSkip2Binding


class SkipFragment2 : Fragment() {
    private lateinit var binding : FragmentSkip2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSkip2Binding.inflate(inflater,container,false)

        binding.tvSkip2.setOnClickListener {


        }
        // Inflate the layout for this fragment
        return binding.root
    }


}