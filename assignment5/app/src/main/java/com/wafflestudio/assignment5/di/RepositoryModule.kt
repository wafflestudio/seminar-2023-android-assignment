package com.wafflestudio.assignment5.di

import com.wafflestudio.assignment5.data.MovieRepository
import com.wafflestudio.assignment5.data.MovieRepositoryImpl
import com.wafflestudio.assignment5.lib.network.api.MovieRestApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsMovieRepository(impl: MovieRepositoryImpl): MovieRepository

}