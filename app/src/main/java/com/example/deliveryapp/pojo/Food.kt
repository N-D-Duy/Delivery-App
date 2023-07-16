package com.example.deliveryapp.pojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val foodId:String? = null,
    val foodName:String? = null,
    val price:String? = null,
    val available: Boolean? = false,
    val category: String? = null,
    val description:String? = null,
    val nutrition: Nutrition? = null,
    val rate:String? = null,
    val restaurantId:String? = null,
    val offerId: String? = null,
    val cartFoodId:String? = null
) : Parcelable