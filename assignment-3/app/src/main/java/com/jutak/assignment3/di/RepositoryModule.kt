package com.jutak.assignment3.di

import com.jutak.assignment3.data.repository.WordListRepository
import com.jutak.assignment3.data.repository.WordListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsWordListRepository(
        impl: WordListRepositoryImpl
    ): WordListRepository
}