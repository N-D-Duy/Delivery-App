package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserById(userId: String): Flow<UiState<User?>>

    fun getAllUser(): Flow<UiState<List<User?>?>>


    fun loginUser(email: String, password: String): Flow<UiState<User?>>


    fun registerUser(email: String, password: String): Flow<UiState<User>>


    fun logoutUser(userId: String): Flow<UiState<String>>

    fun deleteUser(userId: String): Flow<UiState<String>>


    fun updateUser(userId: String, user: User): Flow<UiState<String>>

    fun insertUser(user: User): Flow<UiState<String>>

    fun insertListUser(users: List<User>): Flow<UiState<String>>

    fun getUserByName(name: String): Flow<UiState<User?>>

}