package com.example.deliveryapp.repository

import com.example.deliveryapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface ImagesRepository{
    fun getImagesByFoodId(foodId: String):Flow<Result<List<String>>>

    fun addImage(foodId: String): Flow<UiState<Unit>>

    fun removeImage(foodId: String): Flow<UiState<Unit>>
}