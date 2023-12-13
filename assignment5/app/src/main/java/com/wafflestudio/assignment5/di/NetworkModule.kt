package com.wafflestudio.assignment5.di

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wafflestudio.assignment5.lib.network.api.MovieRestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class) // 앱 전체 수명 주기 동안 단일 인스턴스 (싱글톤)
class NetworkModule {

    @Provides
    fun provideApiKey(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString("apiKey", "") ?: ""
    }

    @Provides // OkHttpClient 타입의 객체를 어떻게 만드는 지 dagger에게 알려 준다.
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYzg5MWYxNWM4ZjU1NzI1YjE3NDA2Nzc3NGUzMzI0YiIsInN1YiI6IjY1NTQ4ZWE1NTM4NjZlMDBhYmFhYTk3MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tuus7_u6jcLbVRl9UpWxF-uyEyR04J7UyAQRKfgf5Sw"
        val builder = OkHttpClient.Builder()
        if (apiKey.isNotEmpty()) {
            builder.addInterceptor { chain ->
                val newRequest = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(newRequest)
            }
        }
        return builder
    }

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun MovieRestApi(
        retrofit: Retrofit,
    ): MovieRestApi {
        return retrofit.create(MovieRestApi::class.java)
    }
}