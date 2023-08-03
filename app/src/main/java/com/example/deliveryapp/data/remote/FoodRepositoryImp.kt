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

class  FoodRepositoryImp(val database: FirebaseFirestore) : FoodRepository {
    //to do
    override suspend fun update(food: Food, foodId: String): UiState<String> {
            UiState.Loading
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

                return UiState.Success("update success")
            } else {
                return UiState.Error("khong tim thay food voi id tuong ung")
            }
    }

    override suspend fun addFood(food: Food, result: (UiState<String>) -> Unit){
        val foodRef = database.collection(FirebaseCollections.FOOD).document()
        foodRef.set(food)
            .addOnSuccessListener {
                result.invoke(UiState.Success("add food successful"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error(exception.message.toString()))
            }
    }

    override suspend fun addListFood(foods: List<Food>, result: (UiState<String>) -> Unit){

    }

    override suspend fun removeFood(foodId: String, result: (UiState<String>) -> Unit){
        val foodRef = database.collection(FirebaseCollections.FOOD).document(foodId)
        foodRef.delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("remove food successful"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error(exception.message.toString()))
            }
    }

    override suspend fun getFoodList(result: (UiState<List<Food>>)->Unit){
        val foodRef = database.collection(FirebaseCollections.FOOD)
            .get()
            .addOnSuccessListener {
                val foodList = arrayListOf<Food>()
                for (food in it) {
                    foodList.add(food.toObject(Food::class.java))
                }
                result.invoke(UiState.Success(foodList))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("get food list failed ${exception.message}"))
            }
    }

    override suspend fun getFoodById(foodId:String, result: (UiState<Food?>)->Unit){
        val foodRef = database.collection(FirebaseCollections.FOOD).document(foodId)
            .get()
            .addOnSuccessListener {
                result.invoke(UiState.Success(it.toObject(Food::class.java)))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("get food failed ${exception.message}"))
            }
    }
}