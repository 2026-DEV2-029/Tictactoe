package com.bnp.tictactoe.di

import com.bnp.tictactoe.data.GameRepositoryImpl
import com.bnp.tictactoe.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Friday, January 30, 2026
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameRepository(): GameRepository {
        return GameRepositoryImpl()
    }
}