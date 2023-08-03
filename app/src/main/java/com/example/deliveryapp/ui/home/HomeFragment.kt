package com.example.deliveryapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryapp.databinding.FragmentHomeBinding
import com.example.deliveryapp.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()
    /*private val viewModel:HomeViewModel by viewModels()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        _binding.btnTest.setOnClickListener {
            getData(viewModel)
        }
        return _binding.root
    }

    private fun getData(viewModel: HomeViewModel) {

        viewModel.test.observe(viewLifecycleOwner){uiState->
            when(uiState){
                is UiState.Loading->{
                    Log.e("Test", "Loading")
                }
                is UiState.Success->{
                    Log.e("Test", "Success")
                }
                is UiState.Error->{
                    Log.e("Test", "Error")
                }
            }
        }
    }

}