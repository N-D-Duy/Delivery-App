package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId: String): Flow<Result<User>>


    fun loginUser(email: String, password: String): Flow<Result<User>>


    fun registerUser(email: String, password: String): Flow<Result<User>>


    fun logoutUser(userId: String): Flow<UiState<Unit>>

    fun deleteUser(userId: String): Flow<UiState<Unit>>


    fun updateUser(userId: String, user: User): Flow<UiState<Unit>>
}