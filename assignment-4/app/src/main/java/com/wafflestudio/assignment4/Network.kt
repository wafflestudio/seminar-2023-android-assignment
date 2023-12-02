package com.wafflestudio.assignment4

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
class Network {

    @Provides
    @Singleton
    fun provideJsonParser(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val modifiedRequest = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(modifiedRequest)
            }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(
        httpClient: OkHttpClient,
        jsonParser: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(jsonParser))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofitService: Retrofit
    ): RestAPI {
        return retrofitService.create(RestAPI::class.java)
    }
}
