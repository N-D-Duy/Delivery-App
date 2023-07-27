package com.example.deliveryapp.data.local.database


import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface DbHelper {
    //user
    fun getAllUsers(): Flow<List<User>>

    fun insertUser(user: User): Flow<UiState<Unit>>

    fun insertAllUser(users: List<User>): Flow<UiState<Unit>>

    fun getUserById(id: String): Flow<User?>

    fun deleteUserById(id: String): Flow<UiState<Unit>>

    fun updateUser(user: User): Flow<UiState<Unit>>


    //food
    fun getAlFoods(): Flow<List<Food>>

    fun insertFood(food: Food): Flow<UiState<Unit>>

    fun insertAllFood(foods: List<Food>): Flow<UiState<Unit>>

    fun getFoodById(id: String): Flow<Food?>

    fun deleteFoodById(id: String): Flow<UiState<Unit>>

    fun updateFood(food: Food): Flow<UiState<Unit>>


    //history
    fun getHistory(uid: String): Flow<History>

    fun updateHistory(history: History): Flow<UiState<Unit>>

    fun getAllHistory(): Flow<List<History>>

}
