package com.holderzone.library.composescaffold.config.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

interface RetrofitConfig {
    fun initRetrofit(baseUrl:String):Retrofit

    fun initOkHttpClient():OkHttpClient

    fun initConverterFactory(): Converter.Factory

}