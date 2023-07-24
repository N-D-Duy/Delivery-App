package com.example.deliveryapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliveryapp.model.History

@Dao
interface HistoryDao {
    @Query("Select * from history where id like :userId")
    fun getHistoryByUserId(userId: String?): History?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateHistory(history: History?)

    @Query("DELETE FROM history WHERE id = :userId")
    fun deleteHistoryForUser(userId: String)
}