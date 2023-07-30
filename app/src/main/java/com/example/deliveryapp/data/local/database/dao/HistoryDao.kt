package com.example.deliveryapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliveryapp.model.History

@Dao
interface HistoryDao {
    @Query("Select * from history where uid like :uid")
    fun getHistoryByUserId(uid: String?): History

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrSetDefaultUser(history: History)


    //bỏ, thay bằng việc update default cho history
    @Query("DELETE FROM history WHERE uid = :uid")
    fun deleteHistoryForUser(uid: String)

    @Query("Select * from history")
    fun getAllHistory(): List<History>
}