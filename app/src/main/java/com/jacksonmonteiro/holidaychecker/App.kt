package com.jacksonmonteiro.holidaychecker

import android.app.Application
import com.jacksonmonteiro.holidaychecker.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(dataModule)
        }
    }
}