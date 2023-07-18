package com.example.deliveryapp.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    val query:String? = null,
    @ServerTimestamp
    val time: Date = Date()
) : Parcelable