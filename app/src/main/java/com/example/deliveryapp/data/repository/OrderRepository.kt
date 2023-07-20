package com.example.deliveryapp.data.repository

import com.example.deliveryapp.model.Order
import com.example.deliveryapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun updateStatusOrder(newStatus: String, userId: String): Flow<UiState<Unit>>

    fun getOrder(userId:String): Flow<Result<Order>>

    fun addOrder(order: Order, userId: String): Flow<UiState<Unit>>

    fun removeOrder(orderId: String): Flow<UiState<Unit>>

}