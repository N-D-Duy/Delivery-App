package com.example.deliveryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    val cartId:String? = null,
    val userId:String? = null,
    val foodList:List<Food>? = null
) : Parcelable