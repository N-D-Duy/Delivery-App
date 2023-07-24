package com.example.deliveryapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "food")
data class Food(
    @PrimaryKey
    val foodId:String? = null,

    @ColumnInfo(name = "food_name")
    val foodName:String? = null,

    @ColumnInfo(name = "price")
    val price:String? = null,

    @ColumnInfo(name = "available")
    val available: Boolean? = false,

    @ColumnInfo(name = "category")
    val category: String? = null,

    @ColumnInfo(name = "description")
    val description:String? = null,

    @ColumnInfo(name = "nutrition")
    val nutrition: Nutrition? = null,

    @ColumnInfo(name = "rate")
    val rate:String? = null,

    @ColumnInfo(name = "restaurant_id")
    val restaurantId:String? = null,

    @ColumnInfo(name = "offer_id")
    val offerId: String? = null,

    @ColumnInfo(name = "cart_id")
    val cartId: String? = null
) : Parcelable