package com.example.deliveryapp.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("history")
data class History(
    @PrimaryKey
    val id:String? = null,

    @ColumnInfo("query")
    val query:MutableMap<String, String>? = null

) : Parcelable