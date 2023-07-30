package com.example.deliveryapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromJsonString(json: String): MutableMap<String, String> {
        val type = object : TypeToken<MutableMap<String, String>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toJsonString(data: MutableMap<String, String>): String {
        return gson.toJson(data)
    }
}