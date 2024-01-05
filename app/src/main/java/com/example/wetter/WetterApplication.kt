package com.example.wetter

import android.app.Application
import com.example.wetter.abstractions.AppContainer
import com.example.wetter.appContainer.AppContainerImpl

class WetterApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}