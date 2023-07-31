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
    override suspend fun getSearchHistory(userId: String, result: (UiState<History>)->Unit) {
        database.collection(FirebaseCollections.HISTORY)
            .document(userId)
            .get()
            .addOnSuccessListener {
                    val history = it.toObject(History::class.java)!!
                   result.invoke(UiState.Success(history))
            }
            .addOnFailureListener {exception ->
                result.invoke(UiState.Error("update failed ${exception.message}"))
            }
    }

    override suspend fun addSearchQuery(userId: String, query: Map<String, Date>, result: (UiState<String>)->Unit) {
            //tham chieu den history cua nguoi dung
            val historyRef = database.collection(FirebaseCollections.HISTORY)
                .document(userId)
                .get()
                .await()
            if(historyRef.exists()){
                database.collection(FirebaseCollections.HISTORY).document(userId).update("queries", query)
                    .addOnSuccessListener {
                        result.invoke(UiState.Success("add search query successful"))
                    }.addOnFailureListener { exception->
                        result.invoke(UiState.Error(exception.message.toString()))
                    }
            }else{
                result.invoke(UiState.Error("history un found"))
            }
    }

    override suspend fun clearSearchHistory(userId: String, result: (UiState<String>) -> Unit) {
        //tham chieu den history can xoa
        val historyRef = database.collection(FirebaseCollections.HISTORY)
            .document(userId)
            historyRef.delete()
                .addOnSuccessListener {
                    result.invoke(UiState.Success("clear history successfully"))
                }
                .addOnFailureListener { e->
                    result.invoke(UiState.Error("delete history failed: ${e.message.toString()}"))
                }
    }

    override suspend fun updateHistory(
        userId: String,
        newQuery: String,
        timestamp: String,
        result: (UiState<String>) -> Unit
    ){
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
                        .addOnSuccessListener{
                            result.invoke(UiState.Success("update history success"))
                        }.addOnFailureListener { e->
                            result.invoke(UiState.Error(e.message.toString()))
                        }

                } else {
                    // History for the user does not exist, create a new document
                    val newHistory = History(userId, mutableMapOf(newQuery to timestamp))
                    historyRef.set(newHistory)
                }
            }
    }
}