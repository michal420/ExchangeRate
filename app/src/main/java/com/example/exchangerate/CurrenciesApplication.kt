package com.example.exchangerate

import android.app.Application
import android.content.Context
import com.example.exchangerate.data.AppContainer
import com.example.exchangerate.data.DefaultAppContainer

class CurrenciesApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        val context: Context = applicationContext
        container = DefaultAppContainer(context)
    }
}