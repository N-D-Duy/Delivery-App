package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface ImagesRepository{
    suspend fun getImagesByFoodId(foodId: String, result: (UiState<ImageFood>)->Unit)

    suspend fun addImage(foodId: String, newUrl:Map<String, String>, result: (UiState<String>)->Unit)

    suspend fun removeImage(foodId: String, urlImage:String, result: (UiState<String>) -> Unit)
}