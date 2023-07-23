package com.example.deliveryapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.deliveryapp.model.Food

@Dao
interface FoodDao{
    @Delete
    fun deleteFood(vararg foods: Food?)

    @Query("Select * from food")
    fun getAllFood(): List<Food?>?

    @Query("Select * from food where foodId IN (:foodIds)")
    fun getFood(foodIds: String): Food?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: Food?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(foods: List<Food?>?)

    @Update
    fun updateFood(vararg foods: Food?)

    @Query("Select * from food where food_name like :name")
    fun getFoodByName(name: String?)
}