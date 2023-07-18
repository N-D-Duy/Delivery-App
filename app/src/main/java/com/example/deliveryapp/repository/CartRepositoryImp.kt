package com.example.deliveryapp.repository

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.util.FirebaseCollections
import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Error

class CartRepositoryImp(val database: FirebaseFirestore): CartRepository{
    //
    override fun getCart(userId: String): Flow<Result<Cart?>> {
        return flow {
            //tìm cart
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()

            // Kiểm tra nếu có cart tồn tại
            if (!cartSnapshot.isEmpty) {
                val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                emit(Result.success(cart))
            } else {
                emit(Result.failure(Throwable("Cart not found")))
            }
        }.catch { exception ->
            emit(Result.failure(Throwable("An error occurred: ${exception.message}")))
        }
    }

    override fun addToCart(userId: String, foodId: String, quantity: Int): Flow<UiState<Unit>> {
        return flow {
            //tìm cart
            emit(UiState.Loading)
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()
            //tham chiếu food_list và thêm entry mới
            if (!cartSnapshot.isEmpty) {
                val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                val newFood:MutableMap<String, Int> = mutableMapOf(
                    foodId to quantity
                )
                val cartId = cart!!.cartId!!
                database.collection(FirebaseCollections.CART).document(cartId).update("food_list", newFood)
                emit(UiState.Success(Unit))
            } else {
                emit(UiState.Error("Cart not found"))
            }
        }
    }

    override fun removeFromCart(userId: String, foodId: String): Flow<UiState<Unit>> {
        return flow {
            //tìm cart
            emit(UiState.Loading)
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()
            //tham chiếu food_list và xóa một entry (update lại food_list)
            if (!cartSnapshot.isEmpty) {
                val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                val cartId = cart!!.cartId!!
                val updateMap = mapOf<String, Any>(
                    "food_list.$foodId" to FieldValue.delete()
                )
                database.collection(FirebaseCollections.CART).document(cartId)
                    .update(updateMap)
                emit(UiState.Success(Unit))
            } else {
                emit(UiState.Error("Cart not found"))
            }
        }
    }

    override fun updateCartItemQuantity(
        userId: String,
        foodId: String,
        quantity: Int
    ): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun clearCart(userId: String): Flow<UiState<Unit>> {
        return flow {
            //tìm cart
            emit(UiState.Loading)
            val cartSnapshot = database.collection(FirebaseCollections.CART)
                .whereEqualTo("user_id", userId)
                .get()
                .await()
            //tham chiếu food_list và clear nó
            if (!cartSnapshot.isEmpty) {
                val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
                val cartId = cart!!.cartId!!
                val updateMap = mapOf<String, Any>(
                    "food_list" to emptyMap<String, Int>()
                )
                database.collection(FirebaseCollections.CART).document(cartId)
                    .update(updateMap)
                emit(UiState.Success(Unit))
            } else {
                emit(UiState.Error("Cart not found"))
            }
        }
    }
}
