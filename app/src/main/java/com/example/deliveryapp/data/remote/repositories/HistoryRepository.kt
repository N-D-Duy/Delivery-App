package com.example.deliveryapp.data.remote.repositories

import com.example.deliveryapp.model.History
import com.example.deliveryapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.util.Date


interface HistoryRepository{
    fun getSearchHistory(userId: String): Flow<UiState<History?>>

    fun addSearchQuery(userId: String, query: MutableMap<String, Date>): Flow<UiState<String>>

    fun clearSearchHistory(userId: String): Flow<UiState<String>>

    fun updateHistory(userId: String, newQuery: String, timestamp: Date): Flow<UiState<String>>
}