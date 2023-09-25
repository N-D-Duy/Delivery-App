package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.ImageFood
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface ImagesRepository{
    fun getImagesByFoodId(foodId: String): Flow<UiState<ImageFood?>>

    fun addImage(foodId: String, newUrl:String): Flow<UiState<String>>

    fun removeImage(foodId: String, urlImage:String): Flow<UiState<String>>
}