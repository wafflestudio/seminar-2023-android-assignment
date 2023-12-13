package com.wafflestudio.assignment4.di

import com.wafflestudio.assignment4.data.AuthRepository
import com.wafflestudio.assignment4.data.AuthRepositoryImpl
import com.wafflestudio.assignment4.data.MainRepository
import com.wafflestudio.assignment4.data.MainRepositoryImpl
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsUserRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}