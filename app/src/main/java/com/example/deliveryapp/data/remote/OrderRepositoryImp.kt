package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.Order
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class OrderRepositoryImp(val database:FirebaseFirestore): OrderRepository {
    //fetch data order
    override suspend fun updateStatusOrder(newStatus: String, userId: String, result: (UiState<String>)->Unit){
        val orderRef = database.collection(FirebaseCollections.ORDER)
            .document(userId)

        val dataMap = mapOf(
            "status" to newStatus
        )

        orderRef.update(dataMap)
            .addOnSuccessListener {
                //
            }
            .addOnFailureListener { exception ->
                //
            }
    }

    override suspend fun getOrder(userId: String, result: (UiState<Order>) -> Unit) {
        database.collection(FirebaseCollections.USER).document(userId)
            .get()
            .addOnSuccessListener {
                val userData = it.toObject(Order::class.java)!!
                result.invoke(UiState.Success(userData))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("get order failed ${exception.message}"))
            }
    }

    override suspend fun addOrder(order: Order, userId: String, result: (UiState<String>) -> Unit) {
        val orderRef = database.collection(FirebaseCollections.ORDER).document()
        orderRef.set(order)
            .addOnSuccessListener {
                result.invoke(UiState.Success("add order success"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("add order failed ${exception.message}"))
            }
    }

    override suspend fun removeOrder(orderId: String, result: (UiState<String>) -> Unit) {
        val orderRef = database.collection(FirebaseCollections.ORDER)
            .document(orderId)

        orderRef.delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("remove order success"))
            }
            .addOnFailureListener { exception ->
                result.invoke(UiState.Error("remove order failed ${exception.message}"))
            }
    }

}