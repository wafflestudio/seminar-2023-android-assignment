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
@InstallIn(SingletonComponent::class)
class Network {
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideNetworkApi(retrofit: Retrofit): MyRestAPI = retrofit.create(MyRestAPI::class.java)

    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideMoshiConverter(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}
