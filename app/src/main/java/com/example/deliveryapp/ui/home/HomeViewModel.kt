package com.example.deliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryapp.domain.AppRepository
import com.example.deliveryapp.utils.UiState

class HomeViewModel(
    val appData: AppRepository
) : ViewModel() {
    private val _test = MutableLiveData<UiState<String>>()
    val test: LiveData<UiState<String>> get() = _test

    fun test() {

    }
}