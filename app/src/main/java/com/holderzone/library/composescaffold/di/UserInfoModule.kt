package com.holderzone.library.composescaffold.di

import com.holderzone.library.composescaffold.db.AppDatabase
import com.holderzone.library.composescaffold.db.dao.UserInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UserInfoModule {
    @Provides
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }
}