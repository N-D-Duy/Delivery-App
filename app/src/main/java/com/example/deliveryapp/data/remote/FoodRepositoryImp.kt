package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Food
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FoodRepositoryImp(val database: FirebaseFirestore) : FoodRepository {
    //to do
    override fun update(food: Food, foodId: String): Flow<UiState<Unit>> {
        return flow {
            emit(UiState.Loading)
            //tham chiêú đến collection food và update
            val foodSnapshot = database.collection(FirebaseCollections.FOOD)
                .document(foodId)
                .get()
                .await()
            val updateFields = mutableMapOf<String, Any>()
            //check xem người dùng muốn update những dữ liệu gì.
            if (food.cartId != null) {
                updateFields["cart_id"] = food.cartId
            }
            if (food.available != null) {
                updateFields["available"] = food.available
            }
            if (food.foodName != null) {
                updateFields["food_name"] = food.foodName
            }
            if (food.category != null) {
                updateFields["category"] = food.category
            }
            if (food.description != null) {
                updateFields["description"] = food.description
            }
            if (food.nutrition != null) {
                updateFields["nutrition"] = food.nutrition
            }
            if (food.offerId != null) {
                updateFields["offer_id"] = food.offerId
            }
            if (food.price != null) {
                updateFields["price"] = food.price
            }
            if (food.rate != null) {
                updateFields["rate"] = food.rate
            }
            if (food.restaurantId != null) {
                updateFields["restaurant_id"] = food.restaurantId
            }

            if (foodSnapshot.exists()) {
                //tìm thấy food cần update
                //update
                database.collection(FirebaseCollections.FOOD)
                    .document(foodId)
                    .update(updateFields)

                emit(UiState.Success(Unit))
            } else {
                emit(UiState.Error("khong tim thay food voi id tuong ung"))
            }
        }.catch { exception ->
            emit(UiState.Error("An error occurred: ${exception.message}"))

        }
    }

    override fun addFood(food: Food): Flow<UiState<Unit>> = callbackFlow {
        val foodRef = database.collection(FirebaseCollections.FOOD).document()
        foodRef.set(food)
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("update failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun removeFood(foodId: String): Flow<UiState<Unit>> = callbackFlow {
        val foodRef = database.collection(FirebaseCollections.FOOD).document(foodId)
        foodRef.delete()
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit))
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("remove failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun getFoodList(): Flow<Result<List<Food>>> = callbackFlow {
        val foodRef = database.collection(FirebaseCollections.FOOD)
            .get()
            .addOnSuccessListener {
                val foodList = arrayListOf<Food>()
                for (food in it) {
                    foodList.add(food.toObject(Food::class.java))
                }
                channel.trySend(Result.success(foodList))
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(Result.failure(Throwable("get food list failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun getFoodById(foodId: String): Flow<Result<Food?>> = callbackFlow {
        val foodRef = database.collection(FirebaseCollections.FOOD).document(foodId)
            .get()
            .addOnSuccessListener {
                channel.trySend(Result.success(it.toObject(Food::class.java)))
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(Result.failure(Throwable("get food failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()
    }


}