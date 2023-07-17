package com.example.deliveryapp.repository

import com.example.deliveryapp.model.Order
import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrderRepositoryImp(val database:FirebaseFirestore): OrderRepository {
    //fetch data order
    override fun updateStatusOrder(newStatus: String, userId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun getOrder(userId: String): Flow<Result<Order>> {
        return flow {

        }
    }

    override fun addOrder(order: Order, userId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun removeOrder(orderId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }

}