package com.example.deliveryapp.pojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    val cartId:String? = null,
    val userId:String? = null,
    val cartFoodId:String? = null
) : Parcelable