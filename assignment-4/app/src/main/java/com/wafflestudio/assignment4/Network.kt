package com.wafflestudio.assignment4

import NumAdapter
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class Network {

    @Provides
    fun provideMoshiConverter(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(NumAdapter())
            .build()
    }

    @Provides
    fun httpClientProvider(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message ->
                Log.d("HTTP Logger", message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept-Version", "v1")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun retrofitProvider(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun apiServiceCreator(retrofit: Retrofit): MyRestAPI {
        return retrofit.create(MyRestAPI::class.java)
    }
}
