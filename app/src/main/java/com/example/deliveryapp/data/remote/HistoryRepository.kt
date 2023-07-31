package com.example.deliveryapp.data.remote

import com.example.deliveryapp.model.History
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.util.Date


interface HistoryRepository{
    suspend fun getSearchHistory(userId: String, result: (UiState<History>)->Unit)

    suspend fun addSearchQuery(userId: String, query: Map<String, Date>, result: (UiState<String>)->Unit)

    suspend fun clearSearchHistory(userId: String, result: (UiState<String>) -> Unit)

    suspend fun updateHistory(userId: String, newQuery: String, timestamp: String, result: (UiState<String>) -> Unit)
}