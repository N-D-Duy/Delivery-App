package com.example.deliveryapp.pojo
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val id:String? = null,
    val name:String? = null,
    val address:String? = null,
    val phone:String? = null,
    val offerId:String? = null
) : Parcelable {
    public fun updateMenu(){}
}