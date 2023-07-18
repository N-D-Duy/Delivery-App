package com.example.deliveryapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageFood(
    val foodId:String? = null,
    val url:Map<String, String>? = null
) : Parcelable