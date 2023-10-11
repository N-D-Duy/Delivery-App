package com.example.deliveryapp.utils

import androidx.room.TypeConverter
import com.example.deliveryapp.model.Query
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromJsonString(json: String): MutableMap<String, Query> {
        val type = object : TypeToken<MutableMap<String, Query>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun toJsonString(data: MutableMap<String, Query>): String {
        return gson.toJson(data)
    }
}