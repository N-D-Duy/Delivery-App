package com.example.deliveryapp.data.local.database


import com.example.deliveryapp.model.Food
import com.example.deliveryapp.model.History
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface DbHelper {
    //user
    fun getAllUsersLocal(): List<User?>?

    fun insertUserLocal(user: User)

    fun insertAllUserLocal(users: List<User>)

    fun getUserByIdLocal(id: String): User?

    fun deleteUserByIdLocal(id: String)

    fun updateUserLocal(user: User)


    //food
    fun getAllFoodsLocal(): List<Food?>?

    fun insertFoodLocal(food: Food)

    fun insertAllFoodLocal(foods: List<Food>)

    fun getFoodByIdLocal(id: String): Food?

    fun deleteFoodByIdLocal(id: String)

    fun updateFoodLocal(food: Food)


    //history
    fun getHistoryLocal(uid: String): History?

    fun updateHistoryLocal(history: History)

    fun getAllHistoryLocal():List<History?>?

}
