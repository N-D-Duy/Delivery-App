package com.example.deliveryapp.data.local.database

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import javax.inject.Inject


class AppDbHelper @Inject constructor(val database: AppDatabase): DbHelper {
    private val mAppDatabase = database
    override suspend fun getAllUsersLocal(): List<User?>? {
        //trả về list, nếu rỗng sẽ trả về empty list (không phải null)
        return mAppDatabase.userDao().loadAll()
    }

    override suspend fun insertUserLocal(user: User) {
        try {
            mAppDatabase.userDao().insert(user)
        } catch (e: Exception) {
            Throwable("Error inserting user: ${e.message}")
        }
    }

    override suspend fun insertAllUserLocal(users: List<User>) {
        try {
            mAppDatabase.userDao().insertAll(users)
        } catch (e: Exception) {
            Throwable("Error inserting list user: ${e.message}")
        }
    }

    override suspend fun getUserByIdLocal(id: String): User? {
        return mAppDatabase.userDao().findById(id)
    }

    override suspend fun deleteUserByIdLocal(id: String) {
        try {
            mAppDatabase.userDao().delete(id)
        } catch (e: Exception) {
            Throwable("Error delete user: ${e.message}")
        }
    }

    override suspend fun updateUserLocal(user: User) {
        try {
            mAppDatabase.userDao().updateUsers(user)
        } catch (e: Exception){
            Throwable("Error update user: ${e.message}")
        }
    }




    override suspend fun getAllFoodsLocal(): List<Food?>? {
        return mAppDatabase.foodDao().getAllFood()
    }

    override suspend fun insertFoodLocal(food: Food) {
        try {
            mAppDatabase.foodDao().insert(food)
        } catch (e: Exception) {
            Throwable("Error inserting food: ${e.message}")
        }
    }

    override suspend fun insertAllFoodLocal(foods: List<Food>) {
        try {
            mAppDatabase.foodDao().insertAll(foods)
        } catch (e: Exception) {
            Throwable("Error inserting list food: ${e.message}")
        }
    }

    override suspend fun getFoodByIdLocal(id: String): Food? {
        return mAppDatabase.foodDao().getFood(id)
    }

    override suspend fun deleteFoodByIdLocal(id: String) {
        try {
            mAppDatabase.foodDao().deleteFood(id)
        } catch (e: Exception) {
            Throwable("Error delete food: ${e.message}")
        }
    }

    override suspend fun updateFoodLocal(food: Food) {
        try {
            mAppDatabase.foodDao().updateFood(food)
        } catch (e: Exception){
            Throwable("Error update food: ${e.message}")
        }
    }


    override suspend fun getHistoryLocal(uid: String): History? {
        return mAppDatabase.historyDao().getHistoryByUserId(uid)
    }

    override suspend fun updateHistoryLocal(history: History) {
        try {
            mAppDatabase.historyDao().updateOrSetDefaultUser(history)
        } catch (e: Exception){
            Throwable("Error update history: ${e.message}")
        }
    }

    override suspend fun getAllHistoryLocal(): List<History?>? {
        return mAppDatabase.historyDao().getAllHistory()
    }
}