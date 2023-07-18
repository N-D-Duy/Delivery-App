package com.example.deliveryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Offer(
    val id:String? = null,
    val discountFood:String? = null,
    val discountRestaurant: String? = null,
    val freeShip:Boolean? = false
) : Parcelable {
    public fun finalPrice(oldPrice: Float): Float{
        val discount1 = discountFood!!.toInt()
        val discount2 = discountRestaurant!!.toInt()
        return oldPrice * (if (discount1 > discount2) discount1 else discount2) * (if(freeShip!!) 1 else 2)
    }
}