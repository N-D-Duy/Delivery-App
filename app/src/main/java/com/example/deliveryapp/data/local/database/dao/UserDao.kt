package com.example.deliveryapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.deliveryapp.model.User


@Dao
interface UserDao {
    @Delete
    fun delete(vararg users: User?)

    @Query("SELECT * FROM user WHERE user_name LIKE :name LIMIT 1")
    fun findByName(name: String?): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User?>?)

    @Query("SELECT * FROM user")
    fun loadAll(): List<User?>?

    @Update
    fun updateUsers(vararg user: User?)

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: List<String?>?): List<User?>?
}