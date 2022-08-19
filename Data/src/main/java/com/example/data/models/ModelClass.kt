package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

// Модель для базы данных
@Entity(tableName = "my_table")
data class DataBaseModel(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "title") val name: String,
    @ColumnInfo(name = "subtitle") val description: String,
    @ColumnInfo(name = "image") @SerializedName("image_url") val imageAvatar: String? = null,
    ): Serializable
