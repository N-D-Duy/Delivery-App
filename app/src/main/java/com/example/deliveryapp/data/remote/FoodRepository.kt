package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun update(food:Food, foodId:String):UiState<String>

    suspend fun addFood(food:Food, result: (UiState<String>) -> Unit)

    suspend fun addListFood(foods: List<Food>, result: (UiState<String>) -> Unit)

    suspend fun removeFood(foodId:String, result: (UiState<String>) -> Unit)

    suspend fun getFoodList(result: (UiState<List<Food>>)->Unit)

    suspend fun getFoodById(foodId:String, result: (UiState<Food?>)->Unit)

}