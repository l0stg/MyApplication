package com.example.data.di

import androidx.room.Room
import com.example.data.Provider
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(get(),
        Provider::class.java,
        "my_table")
        .build()
    }

    single { get<Provider>().somethingDao() }

}