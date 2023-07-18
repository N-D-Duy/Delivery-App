package com.example.deliveryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutrition(
    val kcal:String? = null,
    val protein:String? = null,
    val carboHydrate:String? = null,
    val sodium:String? = null,
    val potassium:String? = null
) : Parcelable