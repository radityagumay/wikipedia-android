package com.raditya.wikipedia

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WikipediaApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}