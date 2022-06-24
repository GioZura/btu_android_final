package com.example.notiou.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "notesTable")
class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")val noteTitle :String,
    @ColumnInfo(name = "description")val noteDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String) : Parcelable