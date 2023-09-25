package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(userId: String): Flow<UiState<Cart?>>

    fun addToCart(userId: String, foodId: String, quantity: Int): Flow<UiState<String>>

    fun removeFromCart(userId: String, foodId: String): Flow<UiState<String>>

    fun updateCartItemQuantity(userId: String, foodId: String, quantity: Int): Flow<UiState<String>>

    fun clearCart(userId: String): Flow<UiState<String>>
}