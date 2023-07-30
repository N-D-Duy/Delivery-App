package com.example.deliveryapp.model
import android.os.Parcelable

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId:Int,

    @ColumnInfo(name = "address")
    val address:String? = null,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl:String? = null,

    @ColumnInfo(name = "user_name")
    val username: String? = null,

    @ColumnInfo(name = "cart_id")
    val cartId:String? = null,

    @ColumnInfo(name = "email")
    val email:String? = null,

    @ColumnInfo(name = "phone")
    val phone: String? = null
) : Parcelable