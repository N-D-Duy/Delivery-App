package com.example.deliveryapp.utils

import androidx.room.TypeConverter
import com.example.deliveryapp.model.Nutrition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NutritionConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromJson(json: String): Nutrition {
        val type = object : TypeToken<Nutrition>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toJson(nutrition: Nutrition): String {
        return gson.toJson(nutrition)
    }
}