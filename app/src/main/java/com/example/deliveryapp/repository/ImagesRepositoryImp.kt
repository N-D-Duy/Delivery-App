package com.example.deliveryapp.repository

import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImagesRepositoryImp(val database:FirebaseFirestore): ImagesRepository {
    //fetch data
    override fun getImagesByFoodId(foodId: String): Flow<Result<List<String>>> {
        return flow {

        }
    }

    override fun addImage(foodId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun removeImage(foodId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }


}