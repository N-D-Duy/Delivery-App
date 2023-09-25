package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.Order
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun updateStatusOrder(newStatus: String, userId: String): Flow<UiState<String>>

    fun getOrder(userId:String): Flow<UiState<Order?>>

    fun addOrder(order: Order, userId: String): Flow<UiState<String>>

    fun removeOrder(orderId: String): Flow<UiState<String>>

}