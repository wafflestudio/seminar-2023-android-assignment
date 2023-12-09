package com.wafflestudio.assignment5

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor{ chain ->
            val newRequest = chain.request().newBuilder().
            addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOGJiYzRiZmI4MzIyYjZlYzc3NGY4ZDRkNjk3OTI1NyIsInN1YiI6IjY1NGE0OGEzZmQ0ZjgwMDEwMWI3ODgzYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3DQk-AQUBRCiZCATMhbM46in3-ECYMR3Q9DXd2SoPZI")
                .build()
                chain.proceed(newRequest)
            }
            .build()
    }
    @Provides
    fun provideMoshi(): Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    @Provides
    fun movieApi(
        retrofit: Retrofit,
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}