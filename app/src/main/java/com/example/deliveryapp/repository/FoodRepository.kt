package com.example.deliveryapp.repository

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun update(food:Food, foodId:String):Flow<UiState<Unit>>

    fun addFood(food:Food):Flow<UiState<Unit>>

    fun removeFood(foodId:String):Flow<UiState<Unit>>

    fun getFoodList(): Flow<Result<List<Food>>>

    fun getFoodById(foodId:String): Flow<Result<Food?>>

}