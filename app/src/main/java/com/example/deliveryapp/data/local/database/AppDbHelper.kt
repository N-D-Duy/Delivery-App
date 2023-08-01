package com.example.deliveryapp.data.local.database

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AppDbHelper( database: AppDatabase): DbHelper {
    private val mAppDatabase = database
    override suspend fun getAllUsers(result: (UiState<List<User?>?>)->Unit) {
        //trả về list, nếu rỗng sẽ trả về empty list (không phải null)
        try {
            val users = mAppDatabase.userDao().loadAll()
            result.invoke(UiState.Success(users))
        } catch (e: Exception){
            result.invoke((UiState.Error("get list user failed: ${e.message}")))
        }

    }

    override suspend fun insertUser(user: User, result: (UiState<String>) -> Unit) {
        try {
            mAppDatabase.userDao().insert(user)
            result.invoke(UiState.Success("insert user to local success"))
        } catch (e: Exception) {
            result.invoke(UiState.Error("Error inserting user: ${e.message}"))
        }
    }

    override suspend fun insertAllUser(users: List<User>, result: (UiState<String>) -> Unit) {
        try {
            mAppDatabase.userDao().insertAll(users)
            result.invoke(UiState.Success("insert list user to local success"))
        } catch (e: Exception) {
            result.invoke(UiState.Error("Error inserting list user: ${e.message}"))
        }
    }

    override suspend fun getUserById(id: String, result: (UiState<User?>) -> Unit) {
        try {
            val user = mAppDatabase.userDao().findById(id)
            result.invoke(UiState.Success(user))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error get user by id: ${e.message}"))
        }
    }

    override suspend fun deleteUserById(id: String, result: (UiState<String>) -> Unit){
        try {
            mAppDatabase.userDao().delete(id)
            result.invoke(UiState.Success("delete user local success"))
        } catch (e: Exception) {
            result.invoke(UiState.Error("Error delete user: ${e.message}"))
        }
    }

    override suspend fun updateUser(user: User, result: (UiState<String>) -> Unit){
        try {
            mAppDatabase.userDao().updateUsers(user)
            result.invoke(UiState.Success("update user success"))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error update user: ${e.message}"))
        }
    }




    override suspend fun getAllFoods(result: (UiState<List<Food?>?>) -> Unit){
        try {
            val foods = mAppDatabase.foodDao().getAllFood()
            result.invoke(UiState.Success(foods))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error get food list: ${e.message}"))
        }
    }

    override suspend fun insertFood(food: Food, result: (UiState<String>) -> Unit) {
        try {
            mAppDatabase.foodDao().insert(food)
            result.invoke(UiState.Success("insert food to local success"))
        } catch (e: Exception) {
            result.invoke(UiState.Error("Error inserting food: ${e.message}"))
        }
    }

    override suspend fun insertAllFood(foods: List<Food>, result: (UiState<String>) -> Unit) {
        try {
            mAppDatabase.foodDao().insertAll(foods)
            result.invoke(UiState.Success("insert food list to local successful"))
        } catch (e: Exception) {
            result.invoke(UiState.Error("Error inserting list food: ${e.message}"))
        }
    }

    override suspend fun getFoodById(id: String, result: (UiState<Food?>) -> Unit){
        try {
            val food = mAppDatabase.foodDao().getFood(id)
            result.invoke(UiState.Success(food))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error get food by id: ${e.message}"))
        }
    }

    override suspend fun deleteFoodById(id: String, result: (UiState<String>) -> Unit){
        try {
            mAppDatabase.foodDao().deleteFood(id)
            result.invoke(UiState.Success("delete food success"))
        } catch (e: Exception) {
            result.invoke(UiState.Error("Error delete food: ${e.message}"))
        }
    }

    override suspend fun updateFood(food: Food, result: (UiState<String>) -> Unit){
        try {
            mAppDatabase.foodDao().updateFood(food)
            result(UiState.Success("update food successful"))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error update food: ${e.message}"))
        }
    }




    override suspend fun getHistory(uid: String, result: (UiState<History?>) -> Unit){
        try {
            val history = mAppDatabase.historyDao().getHistoryByUserId(uid)
            result.invoke(UiState.Success(history))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error get history by id: ${e.message}"))
        }
    }

    override suspend fun updateHistory(history: History, result: (UiState<String>) -> Unit){
        try {
            mAppDatabase.historyDao().updateOrSetDefaultUser(history)
            result.invoke(UiState.Success("update history successful"))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error update history: ${e.message}"))
        }
    }

    override suspend fun getAllHistory(result: (UiState<List<History?>?>) -> Unit){
        try {
            val histories = mAppDatabase.historyDao().getAllHistory()
            result(UiState.Success(histories))
        } catch (e: Exception){
            result.invoke(UiState.Error("Error get all history: ${e.message}"))
        }
    }
}