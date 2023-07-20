package com.example.deliveryapp.data.repository

import com.example.deliveryapp.model.History
import com.example.deliveryapp.util.UiState
import kotlinx.coroutines.flow.Flow
import java.util.Date


interface HistoryRepository{
    fun getSearchHistory(userId: String): Flow<Result<History>>

    fun addSearchQuery(userId: String, query: Map<String, Date>, ): Flow<UiState<Unit>>

    fun clearSearchHistory(userId: String): Flow<UiState<Unit>>
}