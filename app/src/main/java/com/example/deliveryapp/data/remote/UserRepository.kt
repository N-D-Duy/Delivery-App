package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserById(userId: String, result: (UiState<User>)->Unit)

    suspend fun getAllUser(result: (UiState<List<User>>) -> Unit)


    suspend fun loginUser(email: String, password: String): Flow<Result<User>>


    suspend fun registerUser(email: String, password: String): Flow<Result<User>>


    suspend fun logoutUser(userId: String): Flow<UiState<Unit>>

    suspend fun deleteUser(userId: String, result: (UiState<String>) -> Unit)


    suspend fun updateUser(userId: String, user: User, result: (UiState<String>) -> Unit)

    suspend fun insertUser(user: User, result: (UiState<String>) -> Unit)

    suspend fun insertListUser(users: List<User>, result: (UiState<String>) -> Unit)

    suspend fun getUserByName(name: String, result: (UiState<User>) -> Unit)

}