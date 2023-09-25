package com.example.deliveryapp.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.deliveryapp.utils.MapConverter
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity("history")
@TypeConverters(MapConverter::class)
data class History(
    @PrimaryKey
    val uid: String,

    @ColumnInfo("query")
    var query:MutableMap<String, Date>? = mutableMapOf()

) : Parcelable