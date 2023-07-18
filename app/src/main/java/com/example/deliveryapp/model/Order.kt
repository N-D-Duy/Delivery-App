package com.example.deliveryapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val orderId:String? = null,
    val date:String? = null,
    var status:String? = null,
    val paid:Boolean? = false,
    val foodId:String? = null,
    val userId:String? = null
) : Parcelable