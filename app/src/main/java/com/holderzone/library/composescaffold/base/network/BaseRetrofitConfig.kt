package com.holderzone.library.composescaffold.base.network

import com.example.customer.base.network.BaseConverterFactory
import com.holderzone.library.composescaffold.config.network.retrofit.RetrofitConfig
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

abstract class BaseRetrofitConfig : RetrofitConfig {
    override fun initOkHttpClient(): OkHttpClient = BaseOkHttpClient.create()

    override fun initRetrofit(baseUrl: String): Retrofit = BaseRetrofit.create(baseUrl, this)

    override fun initConverterFactory(): Converter.Factory = BaseConverterFactory.create()
}