package com.example.deliveryapp.model

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Cart(
    val cartId:String? = null,
    val userId:String? = null,
    val foodList:Map<String, String>? = null,
    @ServerTimestamp
    val createAt: Date = Date()
) : Parcelable