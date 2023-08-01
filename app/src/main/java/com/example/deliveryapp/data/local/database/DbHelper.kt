package com.example.deliveryapp.data.local.database


import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface DbHelper {
    //user
    suspend fun getAllUsers(result: (UiState<List<User?>?>)->Unit)

    suspend fun insertUser(user: User, result: (UiState<String>) -> Unit)

    suspend fun insertAllUser(users: List<User>, result: (UiState<String>) -> Unit)

    suspend fun getUserById(id: String, result: (UiState<User?>) -> Unit)

    suspend fun deleteUserById(id: String, result: (UiState<String>) -> Unit)

    suspend fun updateUser(user: User, result: (UiState<String>) -> Unit)


    //food
    suspend fun getAllFoods(result: (UiState<List<Food?>?>) -> Unit)

    suspend fun insertFood(food: Food, result: (UiState<String>) -> Unit)

    suspend fun insertAllFood(foods: List<Food>, result: (UiState<String>) -> Unit)

    suspend fun getFoodById(id: String, result: (UiState<Food?>) -> Unit)

    suspend fun deleteFoodById(id: String, result: (UiState<String>) -> Unit)

    suspend fun updateFood(food: Food, result: (UiState<String>) -> Unit)


    //history
    suspend fun getHistory(uid: String, result: (UiState<History?>) -> Unit)

    suspend fun updateHistory(history: History, result: (UiState<String>) -> Unit)

    suspend fun getAllHistory(result: (UiState<List<History?>?>) -> Unit)

}
