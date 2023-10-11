package com.example.deliveryapp.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    val content:String? = null,
    @ServerTimestamp
    val timestamp: Long = 0L
) : Parcelable{
    companion object {
        fun fromMap(map: Map<String, Any>): Query {
            val content = map["content"] as String
            val timestamp = map["timestamp"] as Long
            return Query(content, timestamp)
        }

        fun toMap(query: Query): Map<String, Any> {
            return mutableMapOf(
                "content" to query.content.toString(),
                "timestamp" to query.timestamp
            )
        }
    }
}

