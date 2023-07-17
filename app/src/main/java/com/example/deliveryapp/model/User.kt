package com.example.deliveryapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId:String? = null,
    val avatarUrl:String? = null,
    val username: String? = null,
    val cartId:String? = null,
    val email:String? = null,
    val phone: String? = null
) : Parcelable