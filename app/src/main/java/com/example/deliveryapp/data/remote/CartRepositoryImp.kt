package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Cart
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class CartRepositoryImp(val database: FirebaseFirestore): CartRepository {
    //
    override suspend fun getCart(userId: String): UiState<Cart?> {
        //tìm cart
        val cartSnapshot = database.collection(FirebaseCollections.CART)
            .whereEqualTo("user_id", userId)
            .get()
            .await()

        // Kiểm tra nếu có cart tồn tại
        if (!cartSnapshot.isEmpty) {
            val cart = cartSnapshot.documents[0].toObject(Cart::class.java)
            return UiState.Success(cart)
        } else {
            return UiState.Error("empty data")
        }
    }

    override suspend fun addToCart(userId: String, foodId: String, quantity: Int): UiState<Unit> {
            //tìm cart
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
            return UiState.Success(Unit)
        } else {
            return UiState.Error("add to cart failed")
        }
    }

    override suspend fun removeFromCart(userId: String, foodId: String): UiState<Unit> {
            //tìm cart
        UiState.Loading
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
            return UiState.Success(Unit)
        } else {
            return UiState.Error("Cart not found")
        }
    }

    override suspend fun updateCartItemQuantity(
        userId: String,
        foodId: String,
        quantity: Int
    ): UiState<Unit> {
        return UiState.Loading
    }

    override suspend fun clearCart(userId: String): UiState<Unit> {
            //tìm cart
        UiState.Loading
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
            return UiState.Success(Unit)
        } else {
            return UiState.Error("Cart not found")
        }
    }
}
