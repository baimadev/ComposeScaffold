package com.holderzone.library.composescaffold.network

import com.holderzone.library.composescaffold.base.network.BaseOkHttpClient
import com.holderzone.library.composescaffold.base.network.BaseRetrofitConfig
import com.holderzone.library.composescaffold.network.interceptor.HolderHeaderInterceptor
import okhttp3.OkHttpClient

class HolderRetrofitConfig : BaseRetrofitConfig() {
    override fun initOkHttpClient(): OkHttpClient {
        return BaseOkHttpClient.create(HolderHeaderInterceptor())
    }
}