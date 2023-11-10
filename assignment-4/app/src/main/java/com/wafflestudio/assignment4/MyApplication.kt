package com.wafflestudio.assignment4

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
@HiltAndroidApp
class MyApplication: Application(){

    override fun onCreate() {
        Companion.preferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var preferences : MySharedPreferences
    }
}