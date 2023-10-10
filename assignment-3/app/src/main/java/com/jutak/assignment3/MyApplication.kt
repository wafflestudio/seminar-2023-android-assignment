package com.jutak.assignment3

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApplication: Application() {
    private val client = OkHttpClient.Builder().build() // OkHttpClient 객체 생성
    private val moshi = Moshi.Builder() // Moshi 객체 생성
        .add(KotlinJsonAdapterFactory()) // JSON 직렬화/역직렬화를 해 주는 Adapter 추가
        .build()
    private val retrofit = Retrofit.Builder() // Retrofit 객체 생성
        .client(client) // okhttp 장착
        .baseUrl("http://ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/") // 서버 base url
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // moshi 장착
        .build()
    val restAPI = retrofit.create(MyRestAPI::class.java)
}