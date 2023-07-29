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
    override fun updateStatusOrder(newStatus: String, userId: String): Flow<UiState<Unit>> = callbackFlow {
        val orderRef = database.collection(FirebaseCollections.ORDER)
            .document(userId)

        val order = getOrder(userId) as Order

        order.status = newStatus

        val dataMap = mapOf(
            "order_id" to order.orderId,
            "status" to order.status,
            "paid" to order.paid,
            "date_payment" to order.date
        )

        orderRef.update(dataMap)
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("update status order failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun getOrder(userId: String): Flow<Result<Order>> = callbackFlow {
        database.collection(FirebaseCollections.USER).document(userId)
            .get()
            .addOnSuccessListener {
                val userData = it.toObject(Order::class.java)!!
                channel.trySend(Result.success(userData))
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(Result.failure(Throwable("get order failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun addOrder(order: Order, userId: String): Flow<UiState<Unit>> = callbackFlow {
        val orderRef = database.collection(FirebaseCollections.ORDER).document()
        orderRef.set(order)
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("add order failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun removeOrder(orderId: String): Flow<UiState<Unit>> = callbackFlow {
        val orderRef = database.collection(FirebaseCollections.ORDER)
            .document(orderId)

        orderRef.delete()
            .addOnSuccessListener {
                channel.trySend(UiState.Success(Unit)).isSuccess
                channel.close()
            }
            .addOnFailureListener { exception ->
                channel.trySend(UiState.Error("remove order failed ${exception.message}")).isSuccess
                channel.close()
            }
        awaitClose()
    }

}