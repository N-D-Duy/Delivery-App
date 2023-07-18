package com.example.deliveryapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val id:String? = null,
    val query:Map<String, String>? = null
) : Parcelable