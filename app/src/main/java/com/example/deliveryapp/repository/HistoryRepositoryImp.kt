package com.example.deliveryapp.repository

import com.example.deliveryapp.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class HistoryRepositoryImp(val database:FirebaseFirestore): HistoryRepository {
    //to do
    override fun getSearchHistory(userId: String): Flow<Result<List<Map<String, Date>>>> {
        return flow {

        }
    }

    override fun addSearchQuery(userId: String, query: Map<String, Date>): Flow<UiState<Unit>> {
        return flow {

        }
    }

    override fun clearSearchHistory(userId: String): Flow<UiState<Unit>> {
        return flow {

        }
    }


}