package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCart(userId: String): UiState<Cart?>

    suspend fun addToCart(userId: String, foodId: String, quantity: Int): UiState<Unit>

    suspend fun removeFromCart(userId: String, foodId: String): UiState<Unit>

    suspend fun updateCartItemQuantity(userId: String, foodId: String, quantity: Int): UiState<Unit>

    suspend fun clearCart(userId: String): UiState<Unit>
}