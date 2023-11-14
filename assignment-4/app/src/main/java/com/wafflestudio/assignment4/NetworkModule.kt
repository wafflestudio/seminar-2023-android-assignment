package com.wafflestudio.assignment4

import NumberAdapter
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { log -> Log.d("okhttp3", "HTTP: $log") }
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            )
            .addInterceptor { chain ->
                val newRequest = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(newRequest)
            }.build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(NumberAdapter())
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
    fun MyRestAPI(
        retrofit: Retrofit,
    ): MyRestAPI {
        return retrofit.create(MyRestAPI::class.java)
    }
}