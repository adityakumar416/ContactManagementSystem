package com.example.yourcms.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contact_table")
data class ContactData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name:String,
    val number: String

    ) : Parcelable
