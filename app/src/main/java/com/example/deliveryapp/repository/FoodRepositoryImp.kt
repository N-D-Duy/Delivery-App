package com.example.deliveryapp.repository

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImp(val database:FirebaseFirestore): FoodRepository {
    //to do
    override fun update(food: Food): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun addFood(food: Food): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun removeFood(foodId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun getFoodList(): Flow<Result<List<Food>>> {
        return flow {

        }
    }

    override fun getFoodById(foodId: String): Flow<Result<Food?>> {
        return flow {

        }
    }

}