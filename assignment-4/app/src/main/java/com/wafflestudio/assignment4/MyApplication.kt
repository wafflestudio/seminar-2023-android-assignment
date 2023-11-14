package com.wafflestudio.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    companion object {
        lateinit var preferences : MySharedPreferences
    }

    override fun onCreate() {
        preferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}