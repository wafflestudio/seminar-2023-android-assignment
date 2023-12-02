package com.wafflestudio.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var sharedPrefs: SharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        initializePreferences()
    }

    private fun initializePreferences() {
        sharedPrefs = SharedPreference(applicationContext)
    }
}
