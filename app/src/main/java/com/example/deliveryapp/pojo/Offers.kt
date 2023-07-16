package com.example.deliveryapp.pojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Offers(
    val id:String? = null,
    val discountFood:String? = null,
    val discountRestaurant: String? = null,
    val freeShip:Boolean? = false
) : Parcelable {
    public fun finalPrice(){}
}