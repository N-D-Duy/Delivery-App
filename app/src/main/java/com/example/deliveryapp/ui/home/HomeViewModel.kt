package com.example.deliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.domain.AppRepository
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import com.facebook.internal.Mutable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    val appData: AppRepository
) : ViewModel() {
    private val _test = MutableStateFlow<UiState<List<User?>?>>(UiState.Loading)
    val test = _test.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        val result: Flow<UiState<List<User?>?>> = appData.getAllUser()
        viewModelScope.launch {
            result.collect{
                _test.value = it
            }
        }
    }
}