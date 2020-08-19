package com.stenleone.fitapp

import android.app.Application
import android.content.Context
import com.stenleone.fitapp.koin.appComponent
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class App: Application() {

    companion object: KoinComponent{
        val contextComponent: Context by inject()
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    fun initKoin(){
        startKoin(
            this,
            appComponent
        )
    }
}