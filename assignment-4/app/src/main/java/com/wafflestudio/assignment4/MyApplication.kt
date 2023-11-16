package com.wafflestudio.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
//@Suppress("DEPRECATION")
class MyApplication: Application() {
    companion object {
        lateinit var preferences : SharedPreference
    }

    override fun onCreate() {
        preferences = SharedPreference(applicationContext)
        super.onCreate()
    }
}