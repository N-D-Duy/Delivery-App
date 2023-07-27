package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCart(userId: String): Flow<Result<Cart?>>

    fun addToCart(userId: String, foodId: String, quantity: Int): Flow<UiState<Unit>>

    fun removeFromCart(userId: String, foodId: String): Flow<UiState<Unit>>

    fun updateCartItemQuantity(userId: String, foodId: String, quantity: Int): Flow<UiState<Unit>>

    fun clearCart(userId: String): Flow<UiState<Unit>>
}