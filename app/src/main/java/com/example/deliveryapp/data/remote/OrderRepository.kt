package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Order
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun updateStatusOrder(newStatus: String, userId: String, result: (UiState<String>)->Unit)

    suspend fun getOrder(userId:String, result: (UiState<Order>)->Unit)

    suspend fun addOrder(order: Order, userId: String, result: (UiState<String>) -> Unit)

    suspend fun removeOrder(orderId: String, result: (UiState<String>) -> Unit)

}