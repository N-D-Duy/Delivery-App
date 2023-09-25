package com.example.deliveryapp.domain

data class DataInfoState(
    val dataItems: List<Any> = emptyList(),
    val isLoading: Boolean = false
)
