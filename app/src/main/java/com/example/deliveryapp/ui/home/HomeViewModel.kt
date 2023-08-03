package com.example.deliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.data.AppData
import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.Nutrition
import com.example.deliveryapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    val appData: AppData
) : ViewModel() {
    private val _test = MutableLiveData<UiState<String>>()
    val test: LiveData<UiState<String>> get() = _test

    fun test() {
        _test.value = UiState.Loading
        viewModelScope.launch {
            _test.value = appData.update(
                Food(
                    123456,
                    "mon an 1",
                    "1000",
                    false,
                    "ngon",
                    "monan1 monan1",
                    Nutrition("100", "15", "15", "15", "15"),
                    "5",
                    "01234",
                    "0123",
                    "012"
                ), "123456"
            )
        }
    }
}