package com.jutak.assignment3

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@HiltAndroidApp
class MyApplication: Application() {
    private val client = OkHttpClient.Builder().build()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
