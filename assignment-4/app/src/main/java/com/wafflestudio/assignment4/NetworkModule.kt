package com.wafflestudio.assignment4

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule{
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor{
                chain ->
                val newRequest: Request =
                    chain.request().newBuilder().
                    build()
                chain.proceed(newRequest)
            }
            .build()
    }
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    @Provides
    fun MovieApi(
        retrofit: Retrofit,
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}