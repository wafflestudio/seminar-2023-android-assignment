package com.wafflestudio.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    companion object{
        lateinit var preference:MySharedPreference
    }
    override fun onCreate() {
        super.onCreate()
        preference = MySharedPreference(applicationContext)
    }


}