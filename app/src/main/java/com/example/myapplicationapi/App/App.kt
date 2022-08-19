package com.example.myapplicationapi.App

import android.app.Application
import com.example.data.Provider
import com.example.data.di.dataBaseModule
import com.example.myapplicationapi.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //Provider.create(this)

        // Создание коина и обьявление модулей
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule, dataBaseModule)
        }
    }
}