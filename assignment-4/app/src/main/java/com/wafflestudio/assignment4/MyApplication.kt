package com.wafflestudio.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object{
        lateinit var prefs:SharedPreference
    }

    override fun onCreate() {
        prefs=SharedPreference(applicationContext)
        super.onCreate()
    }
}