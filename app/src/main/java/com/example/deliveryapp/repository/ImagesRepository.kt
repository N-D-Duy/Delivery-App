package com.example.deliveryapp.repository

import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface ImagesRepository{
    fun getImagesByFoodId(foodId: String):Flow<Result<ImageFood>>

    fun addImage(foodId: String, newUrl:Map<String, String>): Flow<UiState<Unit>>

    fun removeImage(foodId: String, urlImage:String): Flow<UiState<Unit>>
}