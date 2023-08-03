package com.example.deliveryapp.data.local.database


import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface DbHelper {
    //user
    suspend fun getAllUsersLocal(): List<User?>?

    suspend fun insertUserLocal(user: User)

    suspend fun insertAllUserLocal(users: List<User>)

    suspend fun getUserByIdLocal(id: String): User?

    suspend fun deleteUserByIdLocal(id: String)

    suspend fun updateUserLocal(user: User)


    //food
    suspend fun getAllFoodsLocal(): List<Food?>?

    suspend fun insertFoodLocal(food: Food)

    suspend fun insertAllFoodLocal(foods: List<Food>)

    suspend fun getFoodByIdLocal(id: String): Food?

    suspend fun deleteFoodByIdLocal(id: String)

    suspend fun updateFoodLocal(food: Food)


    //history
    suspend fun getHistoryLocal(uid: String): History?

    suspend fun updateHistoryLocal(history: History)

    suspend fun getAllHistoryLocal():List<History?>?

}
