package com.example.jet


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
@InstallIn(SingletonComponent::class) // 앱 전체 수명 주기 동안 단일 인스턴스 (싱글톤)
class NetworkModule {
    @Provides // OkHttpClient 타입의 객체를 어떻게 만드는 지 dagger에게 알려 준다.
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request()
                    .newBuilder()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDA3YTQ0MjU1NGM5ZTNiM2E1NmVhNzQ1MmVkYTNjYiIsInN1YiI6IjY1NTcxODg0ZWE4NGM3MTA5MjI4OTFkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tk7NnUpEzXoD-8aaCJw4L-neabtM56D_-WvgB2T50eo")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
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
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org")
            .build()
    }

    @Provides
    fun MyRestAPI(
        retrofit: Retrofit,
    ): MyRestAPI {
        return retrofit.create(MyRestAPI::class.java)
    }
}