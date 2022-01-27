package com.holderzone.library.composescaffold.di

import com.holderzone.library.composescaffold.BuildConfig
import com.holderzone.library.composescaffold.config.EnvPrefKeys
import com.holderzone.library.composescaffold.config.network.retrofit.RetrofitFactory
import com.holderzone.library.composescaffold.network.ApiHelper
import com.holderzone.library.composescaffold.network.HolderRetrofitConfig
import com.holderzone.library.composescaffold.network.OperationApiHelper
import com.holderzone.library.composescaffold.util.XPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {
    @Provides
    fun provideApiHelper(): ApiHelper {
        return RetrofitFactory.create(
            ApiHelper::class.java,
            "https://www.baidu.com",
            HolderRetrofitConfig()
        )
    }

    @Provides
    fun provideMemberApiHelper(): OperationApiHelper{
        return RetrofitFactory.create(
            OperationApiHelper::class.java,  "https://www.baidu.com",
            HolderRetrofitConfig()
        )
    }
}