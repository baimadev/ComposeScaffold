package com.holderzone.library.composescaffold.config.network.retrofit

import com.holderzone.library.composescaffold.base.network.BaseRetrofitConfig

object RetrofitFactory {

    fun <T> create(clazz: Class<T>, baseUrl: String, retrofitConfig: BaseRetrofitConfig? = null): T {
        val retrofit = retrofitConfig?.initRetrofit(baseUrl)
                ?: DefaultRetrofitConfig().initRetrofit(baseUrl)

        return retrofit.create(clazz)
    }
}