package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.History
import com.example.deliveryapp.utils.FirebaseCollections
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
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

    override fun updateHistory(
        userId: String,
        newQuery: String,
        timestamp: String
    ): Flow<UiState<Unit>> = callbackFlow{
        val historyRef = database.collection(FirebaseCollections.HISTORY)
            .document(userId)
            historyRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // History for the user already exists
                    val historyData = documentSnapshot.toObject(History::class.java)
                    // Update the queries map with the new query and timestamp
                    historyData?.query?.put(newQuery, timestamp)

                    // Update the "queries" field in the Firestore document
                    historyRef.set(historyData as History)
                } else {
                    // History for the user does not exist, create a new document
                    val newHistory = History(userId, mutableMapOf(newQuery to timestamp))
                    historyRef.set(newHistory)
                }
            }
    }
}