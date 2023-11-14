package com.wafflestudio.assignment4

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor{chain ->
                val newRequest=chain.request()
                    .newBuilder()
                    .addHeader("Authorization","토큰 값")
                    .addHeader("accept","application/json")
                    .build()
                chain.proceed(newRequest)
            }
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ):Retrofit{
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun RestAPI(
        retrofit: Retrofit
    ):RestAPI{
        return retrofit.create(RestAPI::class.java)
    }
}