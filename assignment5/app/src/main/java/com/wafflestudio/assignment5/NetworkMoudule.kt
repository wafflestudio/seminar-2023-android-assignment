package com.wafflestudio.assignment5

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor{chain ->
                val newRequest=chain.request()
                    .newBuilder()
                    .addHeader("accept","application/json")
                    .build()
                chain.proceed(newRequest)
            }.build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
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