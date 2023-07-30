package com.example.deliveryapp.data.local.database

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AppDbHelper( database: AppDatabase): DbHelper {
    private val mAppDatabase = database
    override fun getAllUsers(): Flow<List<User>> = flow {
        //trả về list, nếu rỗng sẽ trả về empty list (không phải null)
        emit(mAppDatabase.userDao().loadAll())
    }

    override fun insertUser(user: User): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.userDao().insert(user)
            emit(UiState.Success(Unit))
        } catch (e: Exception) {
            emit(UiState.Error("Error inserting user: ${e.message}"))
        }
    }

    override fun insertAllUser(users: List<User>): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.userDao().insertAll(users)
            emit(UiState.Success(Unit))
        } catch (e: Exception) {
            emit(UiState.Error("Error inserting list user: ${e.message}"))
        }
    }

    override fun getUserById(id: String): Flow<User?> = flow {
            val user = mAppDatabase.userDao().findById(id)
            emit(user)
    }

    override fun deleteUserById(id: String): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.userDao().delete(id)
            emit(UiState.Success(Unit))
        } catch (e: Exception) {
            emit(UiState.Error("Error delete user: ${e.message}"))
        }
    }

    override fun updateUser(user: User): Flow<UiState<Unit>> = flow{
        try {
            mAppDatabase.userDao().updateUsers(user)
            emit(UiState.Success(Unit))
        } catch (e: Exception){
            emit(UiState.Error("Error update user: ${e.message}"))
        }
    }




    override fun getAllFoods(): Flow<List<Food>> = flow{
        emit(mAppDatabase.foodDao().getAllFood())
    }

    override fun insertFood(food: Food): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.foodDao().insert(food)
            emit(UiState.Success(Unit))
        } catch (e: Exception) {
            emit(UiState.Error("Error inserting food: ${e.message}"))
        }
    }

    override fun insertAllFood(foods: List<Food>): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.foodDao().insertAll(foods)
            emit(UiState.Success(Unit))
        } catch (e: Exception) {
            emit(UiState.Error("Error inserting list food: ${e.message}"))
        }
    }

    override fun getFoodById(id: String): Flow<Food?> = flow{
        val food = mAppDatabase.foodDao().getFood(id)
        emit(food)
    }

    override fun deleteFoodById(id: String): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.foodDao().deleteFood(id)
            emit(UiState.Success(Unit))
        } catch (e: Exception) {
            emit(UiState.Error("Error delete food: ${e.message}"))
        }
    }

    override fun updateFood(food: Food): Flow<UiState<Unit>> = flow {
        try {
            mAppDatabase.foodDao().updateFood(food)
            emit(UiState.Success(Unit))
        } catch (e: Exception){
            emit(UiState.Error("Error update food: ${e.message}"))
        }
    }




    override fun getHistory(uid: String): Flow<History> = flow{
        val history = mAppDatabase.historyDao().getHistoryByUserId(uid)
        emit(history)
    }

    override fun updateHistory(history: History): Flow<UiState<Unit>> = flow{
        try {
            mAppDatabase.historyDao().updateOrSetDefaultUser(history)
            emit(UiState.Success(Unit))
        } catch (e: Exception){
            emit(UiState.Error("Error update history: ${e.message}"))
        }
    }

    override fun getAllHistory(): Flow<List<History>> = flow {
        emit(mAppDatabase.historyDao().getAllHistory())
    }
}