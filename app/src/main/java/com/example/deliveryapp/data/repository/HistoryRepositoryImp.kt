package com.example.deliveryapp.data.repository

import com.example.deliveryapp.model.History
import com.example.deliveryapp.util.FirebaseCollections
import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Date

class HistoryRepositoryImp(val database: FirebaseFirestore) : HistoryRepository {
    //to do
    override fun getSearchHistory(userId: String): Flow<Result<History>> = callbackFlow {
        val historyRef = database.collection(FirebaseCollections.HISTORY)
            .document(userId)
            .get()
            .addOnSuccessListener {
                    val history = it.toObject(History::class.java)!!
                    channel.trySend(Result.success(history))
                    channel.close()
            }
            .addOnFailureListener {exception ->
                channel.trySend(Result.failure(Throwable("update failed ${exception.message}"))).isSuccess
                channel.close()
            }
        awaitClose()
    }

    override fun addSearchQuery(userId: String, query: Map<String, Date>): Flow<UiState<Unit>> {
        return flow {
            emit(UiState.Loading)
            //tham chieu den history cua nguoi dung
            val historyRef = database.collection(FirebaseCollections.HISTORY)
                .document(userId)
                .get()
                .await()
            if(historyRef.exists()){
                database.collection(FirebaseCollections.HISTORY).document(userId).update("queries", query)
                emit(UiState.Success(Unit))
            }else{
                emit(UiState.Error("add query failed"))
            }
        }.catch { e->
            emit(UiState.Error("Error: ${e.message.toString()}"))
        }
    }

    override fun clearSearchHistory(userId: String): Flow<UiState<Unit>> = callbackFlow {
        //tham chieu den history can xoa
        val historyRef = database.collection(FirebaseCollections.HISTORY)
            .document(userId)
            historyRef.delete()
                .addOnSuccessListener {
                    channel.trySend(UiState.Success(Unit))
                    channel.close()
                }
                .addOnFailureListener { e->
                    channel.trySend((UiState.Error("delete history failed: ${e.message.toString()}")))
                    channel.close()
                }
        awaitClose()
    }
}