package com.example.data

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.Daos.Dao
import com.example.data.models.DataBaseModel

// Создание базы данных
@Database(entities = [DataBaseModel::class], version = 1)
abstract class Provider: RoomDatabase() {

   /*companion object {
        var instance: Provider? = null
        fun create(context: Context) {
            instance = Room.databaseBuilder(context, Provider::class.java, "my_table").build()
        }
    }*/
    abstract fun somethingDao(): Dao
}
