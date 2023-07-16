package com.example.deliveryapp.pojo
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageFood(
    val foodId:String? = null,
    val url:String? = null
) : Parcelable