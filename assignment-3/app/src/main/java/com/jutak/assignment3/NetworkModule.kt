package com.jutak.assignment3

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
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder().baseUrl("ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/myapp/v1")
        .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi))
                .build()
    }

    @Provides
    fun MyRestAPI(
        retrofit: Retrofit,
    ): MyRestAPI {
        return retrofit.create(MyRestAPI::class.java)
    }

}