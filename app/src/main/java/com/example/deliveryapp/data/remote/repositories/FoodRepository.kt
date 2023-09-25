package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun updateFood(food:Food, foodId:String):Flow<UiState<String>>

    fun addFood(food:Food): Flow<UiState<String>>

    fun addListFood(foods: List<Food>): Flow<UiState<String>>

    fun removeFood(foodId:String): Flow<UiState<String>>

    fun getFoodList(): Flow<UiState<List<Food?>?>>

    fun getFoodById(foodId:String): Flow<UiState<Food?>>
}